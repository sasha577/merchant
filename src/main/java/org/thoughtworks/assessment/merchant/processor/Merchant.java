package org.thoughtworks.assessment.merchant.processor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumeralDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumberRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.PriceRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.ProductDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestHandler;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;

/**
 * Implements the main work flow of the program.  
 * Receives currencies and products requests and provide replays.
 */
public final class Merchant {

    private final Collection<RequestHandler> revisers;
    
    /**
     * Constructor.
     *
     * @param localNumeralsRegistry a registry for the local numerals.
     * @param romanNumeralsConverter a converter of the Roman numerals.
     * @param productCatalog a catalog of the products.
     */
    public Merchant(
            final LocalNumeralsRegistry localNumeralsRegistry,
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.revisers = createRequestHandlers(localNumeralsRegistry,romanNumeralsConverter,productCatalog);
    }
    
    
    /**
     * Processes the requests.
     *
     * @param in an input stream the requests are read out.
     * @param out an output stream the replies are written to.
     */
    public void process( final Reader in, final Writer out ){
        
        final BufferedReader bin = new BufferedReader(in);
        
        Optional<Request> request;
        
        while( (request=readNextRequest(bin)).isPresent() ){
            
            final Optional<RequestHandler> requestReviser = 
            		findResponsibleRequestReviser(request.get());
            
            if(requestReviser.isPresent()){
                
                final Optional<Reply> reply = requestReviser.get().process(request.get());
                if(reply.isPresent()){
                    writeReplay(reply.get(), out);
                }
            }
            else{
                writeReplay(NO_IDEA_REPLAY, out);
            }
        }
        
    }

    /**
     * Read out the next request from the input stream.
     * Returns empty if there in no more data.
     */
    private Optional<Request> readNextRequest(final BufferedReader in){
        try {
        	
            final String line = in.readLine();
			return line != null ? Optional.of(new Request(line)) : Optional.empty();
			
        } catch (final IOException e) {
            throw new RuntimeException("unable to read from input.", e);
        }
    }

    /**
     * Writes the reply to the output stream. 
     */
    private void writeReplay(final Reply reply, final Writer out){
        try {
            out.write(reply.getValue());
            out.write('\n');
            out.flush();
        } catch (final IOException e) {
            throw new RuntimeException("unable to write out respose.", e);
        }
    }
    
    /**
     * Searches for the request reviser responsible for the request.  
     */
    private Optional<RequestHandler> findResponsibleRequestReviser(final Request request){
        
        return revisers.stream().filter(p -> p.isResposibleFor(request)).findFirst();
    }
    
    /**
     * Factory method.
     * Creates a list of supported request handlers. 
     */
    private static Collection<RequestHandler> createRequestHandlers(
            final LocalNumeralsRegistry localNumeralsRegistry,
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {

        return Arrays.asList(
                new LocalNumeralDefinitionRequestHandler(localNumeralsRegistry),
                new LocalNumberRequestHandler(localNumeralsRegistry,romanNumeralsConverter),
                new PriceRequestHandler(localNumeralsRegistry,romanNumeralsConverter, productCatalog),
                new ProductDefinitionRequestHandler(localNumeralsRegistry,romanNumeralsConverter, productCatalog));
    }

    private static final Reply NO_IDEA_REPLAY = new Reply("I have no idea what you are talking about");

}
