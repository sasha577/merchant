package org.thoughtworks.assessment.merchant.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.LocalNumberRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.LocalNumeralDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.PriceRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.ProductDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.base.RequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.LocalNumberLiteralsRegistryImpl;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.ProductCatalogImpl;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.factory.RomanNumeralsConverterFactory;

/**
 * The business interface to the program.  
 * Receives the currencies and the products requests and provides the replies.
 * 
 * 
 */
public final class Merchant {

    /**
     * the collection of the request handlers.
     */
	private final Collection<RequestHandler> handlers;

	/**
	 * Constructor.
	 *
	 * @param localNumeralsRegistry a registry for the local numerals.
	 * @param romanNumeralsConverter a converter of the Roman numerals.
	 * @param productCatalog a catalog of the products.
	 */
	public Merchant() {

		this.handlers = createRequestHandlers();

	}


	/**
	 * Replies to the requests.
	 * Because not all requests expect a replay the result is optional.
	 */
	public Optional<Reply> process( final Request request ){

		final Optional<RequestHandler> handler = 
				findResponsibleHandler(request);

		return handler.isPresent() ? handler.get().process(request) : NO_IDEA_REPLAY;
	}

	/**
	 * Searches for the request handler responsible for the request.  
	 */
	private Optional<RequestHandler> findResponsibleHandler(final Request request){

		return handlers.stream().filter(p -> p.isResposibleFor(request)).findFirst();
	}

	/**
	 * Creates a list of the request handlers for supported requests. 
	 */
	private static Collection<RequestHandler> createRequestHandlers() {

	    final LocalNumeralsRegistry localNumeralsRegistry = new LocalNumberLiteralsRegistryImpl();
        final RomanNumeralsConverter romanNumeralsConverter = RomanNumeralsConverterFactory.create();
        final ProductCatalog productCatalog = new ProductCatalogImpl();
        
		return Arrays.asList(
				new LocalNumeralDefinitionRequestHandler(localNumeralsRegistry),
				new LocalNumberRequestHandler(localNumeralsRegistry,romanNumeralsConverter),
				new PriceRequestHandler(localNumeralsRegistry,romanNumeralsConverter, productCatalog),
				new ProductDefinitionRequestHandler(localNumeralsRegistry,romanNumeralsConverter, productCatalog));
	}

	private static final Optional<Reply> NO_IDEA_REPLAY = 
			Optional.of(new Reply("I have no idea what you are talking about"));

}
