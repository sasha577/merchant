package org.thoughtworks.assessment.merchant.processor.impl.handlers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.base.RequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName.NotDefinedProductException;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber.WrongRomanNumberException;

/**
 * The handle for the request asking for price of the certain product.
 * For example: 'how many Credits is glob prok Silver ?'. 
 */
 public final class PriceRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumeralsRegistry;
    private final RomanNumeralsConverter romanNumeralsConverter;
    private final ProductCatalog productCatalog;

    /**
     * Constructor.
     *
     * @param localNumeralsRegistry a registry for local numerals.
     * @param romanNumeralsConverter a converter from Roman to Arabic numerals.
     * @param productCatalog a product catalog.
     */
    public PriceRequestHandler(
            final LocalNumeralsRegistry localNumeralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.localNumeralsRegistry = localNumeralsRegistry;
        this.productCatalog = productCatalog;
        this.romanNumeralsConverter = romanNumeralsConverter;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isResposibleFor(final Request request) {

        return REQUEST_PATTERN.matcher(request.getValue()).matches();
    }


    /** {@inheritDoc} */
    @Override
    public Optional<Reply> process(final Request request) {

        try{

            final Pair<LocalNumber, ProductName> requestData = parse(request);
            
            final LocalNumber localNumber = requestData.getFirstValue();
            final ProductName productName = requestData.getSecondValue();

            final RomanNumber romanNumber = 
                    localNumeralsRegistry.toRomanNumber(localNumber);
            
            final int count = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            final PriceInCredits productPrice = productCatalog.getPrice(productName);
            
            final Fraction value = productPrice.getValue();
            
            final int result = value.multiply(Fraction.of(count)).toInteger();
            
            return Optional.of(new Reply(String.format("%s %s is %d Credits", localNumber.toLiteral(), productName.getValue(), result)));

        }catch(final UnknownLiteral|WrongRomanNumberException|NotDefinedProductException e){
            
            return Optional.of(new Reply(e.getMessage()));
        }
    }

    /**
     * Extracts the variable part from the request.
     */
    private static Pair<LocalNumber, ProductName> parse(final Request request){

        final Matcher matcher = REQUEST_PATTERN.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")),LocalNumberLiteral::of);

        final ProductName productName = 
                new ProductName(matcher.group(3));

        return Pair.of(new LocalNumber(literals), productName);
    }

    private static final Pattern REQUEST_PATTERN = 
            Pattern.compile("how many Credits is ((\\w+ )+?)(\\w+) \\?");
}
