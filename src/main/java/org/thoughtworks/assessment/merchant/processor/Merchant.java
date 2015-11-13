package org.thoughtworks.assessment.merchant.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumbeLiteralRequestReviser;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumberRequestReviser;
import org.thoughtworks.assessment.merchant.processor.impl.PriceRequestReviser;
import org.thoughtworks.assessment.merchant.processor.impl.ProductDefinitionRequestReviser;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;

/**
 * <p>Merchant class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class Merchant {

    private final Collection<RequestReviser> revisers;
    
    /**
     * <p>Constructor for Merchant.</p>
     *
     * @param localNumberLiteralsRegistry a {@link org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry} object.
     * @param romanNumeralsConverter a {@link org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter} object.
     * @param productCatalog a {@link org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog} object.
     */
    public Merchant(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry,
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.revisers = createRevisers(localNumberLiteralsRegistry,romanNumeralsConverter,productCatalog);
    }
    
    
    /**
     * <p>process.</p>
     *
     * @param in a {@link java.io.Reader} object.
     * @param out a {@link java.io.Writer} object.
     */
    public void process( final Reader in, final Writer out ){
        
        final BufferedReader bin = new BufferedReader(in);
        
        String str;
        
        while( (str=readln(bin))!=null ){
            
            final Request request = new Request(str);
            final Optional<RequestReviser> requestReviser = findResponsibleRequestReviser(request);
            
            if(requestReviser.isPresent()){
                
                final Optional<Replay> replay = requestReviser.get().process(request);
                if(replay.isPresent()){
                    writeln(out, replay.get());
                }
            }
            else{
                writeln(out, NO_IDEA_REPLAY);
            }
        }
        
    }


    private String readln(final BufferedReader in){
        try {
            return in.readLine();
        } catch (final IOException e) {
            throw new RuntimeException("unable to read from input.", e);
        }
    }


    private void writeln(final Writer out, final Replay replay){
        try {
            out.write(replay.getValue());
            out.write('\n');
            out.flush();
        } catch (final IOException e) {
            throw new RuntimeException("unable to write out respose.", e);
        }
    }
    
    private Optional<RequestReviser> findResponsibleRequestReviser(final Request request){
        
        return revisers.stream().filter(p -> p.isResposibleFor(request)).findFirst();
    }
    
    private static Collection<RequestReviser> createRevisers(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry,
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {

        return Arrays.asList(
                new LocalNumbeLiteralRequestReviser(localNumberLiteralsRegistry),
                new LocalNumberRequestReviser(localNumberLiteralsRegistry,romanNumeralsConverter),
                new PriceRequestReviser(localNumberLiteralsRegistry,romanNumeralsConverter, productCatalog),
                new ProductDefinitionRequestReviser(localNumberLiteralsRegistry,romanNumeralsConverter, productCatalog));
    }

    private static final Replay NO_IDEA_REPLAY = new Replay("I have no idea what you are talking about");

}
