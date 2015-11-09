package org.thoughtworks.assessment.merchant.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
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

public final class Merchant {

    private final Collection<RequestReviser> revisers;
    
    public Merchant(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry,
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.revisers = createRevisers(localNumberLiteralsRegistry,romanNumeralsConverter,productCatalog);
    }
    
    
    public void process( final BufferedReader in, final BufferedWriter out ){
        
        String str;
        
        while( (str=readLine(in))!=null && str.length()!=0 ){
            
            final Request request = new Request(str);
            final Optional<RequestReviser> requestReviser = findResponsibleRequestReviser(request);
            
            if(requestReviser.isPresent()){
                
                final Replay replay = requestReviser.get().process(request);
                write(out, replay);
            }
            else{
                write(out, NO_IDEA_REPLAY);
            }
        }
        
    }


    private String readLine(final BufferedReader in){
        try {
            return in.readLine();
        } catch (final IOException e) {
            throw new RuntimeException("unable to read from input.", e);
        }
    }


    private void write(final BufferedWriter out, final Replay replay){
        try {
            out.write(replay.getValue());
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
