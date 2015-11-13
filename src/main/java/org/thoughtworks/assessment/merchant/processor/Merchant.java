package org.thoughtworks.assessment.merchant.processor;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumberRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.LocalNumeralDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.PriceRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.ProductDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestHandler;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;

/**
 * The business interface to the program.  
 * Receives the currencies and the products requests and provides the replies.
 */
public final class Merchant {

	private final Collection<RequestHandler> handlers;

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

		this.handlers = createRequestHandlers(localNumeralsRegistry,romanNumeralsConverter,productCatalog);
	}


	/**
	 * Replies to the requests.
	 * Because not all requests expect a replay the result is optional.
	 */
	public Optional<Reply> process( Request request ){

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

	private static final Optional<Reply> NO_IDEA_REPLAY = 
			Optional.of(new Reply("I have no idea what you are talking about"));

}
