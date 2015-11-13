package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestHandler;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

/**
 * <p>ProductDefinitionRequestReviser class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class ProductDefinitionRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumeralsRegistry;
    private final RomanNumeralsConverter romanNumeralsConverter;
    private final ProductCatalog productCatalog;

    /**
     * <p>Constructor for ProductDefinitionRequestReviser.</p>
     *
     * @param localNumeralsRegistry a {@link org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry} object.
     * @param romanNumeralsConverter a {@link org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter} object.
     * @param productCatalog a {@link org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog} object.
     */
    public ProductDefinitionRequestHandler(
            final LocalNumeralsRegistry localNumeralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.localNumeralsRegistry = localNumeralsRegistry;
        this.romanNumeralsConverter = romanNumeralsConverter;
        this.productCatalog = productCatalog;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Reply> process(final Request request) {

        final Matcher matcher = IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final Pair<Pair<LocalNumber, ProductName>, Integer> parsedRequest = parseRequest(request,matcher);
        
        final LocalNumber localNumber = parsedRequest.getFirstValue().getFirstValue();
        final ProductName productName = parsedRequest.getFirstValue().getSecondValue();
        
        final int sumPrice = parsedRequest.getSecondValue();

        try{
            
            final RomanNumber romanNumber = 
                    localNumeralsRegistry.toRomanNumber(localNumber);
            
            final int amount = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            productCatalog.addOrReplaceProduct(productName, new PriceInCredits(Fraction.of(sumPrice,amount)));
            
            return Optional.empty();

        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return Optional.of(new Reply(e.getMessage()));
        }
        
    }

    /** {@inheritDoc} */
    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue()).matches();
    }


    private static Pair<Pair<LocalNumber, ProductName>, Integer> parseRequest( 
            final Request request, final Matcher matcher){

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")),LocalNumberLiteral::of);

        final ProductName productName = 
                new ProductName(matcher.group(3));

        final int price = 
                Integer.valueOf(matcher.group(4));

        return Pair.of(Pair.of(new LocalNumber(literals), productName), price);
    }

    private static final Pattern IS_PRODUCT_DEFINITION_REQUEST = 
            Pattern.compile("((\\w+ )+?)(\\w+) is (\\d+) Credits");


}
