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
 * The handle for the request defining the price of the certain product.
 * For example: 'pish pish Iron is 3910 Credits'. 
 */
public final class ProductDefinitionRequestHandler implements RequestHandler{

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
    public boolean isResposibleFor(final Request request) {

        return REQUEST_PATTERN.matcher(request.getValue()).matches();
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Reply> process(final Request request) {

        try{

            final Pair<Pair<LocalNumber, ProductName>, Integer> requestData = parse(request);
            
            final LocalNumber localNumber = requestData.getFirstValue().getFirstValue();
            final ProductName productName = requestData.getFirstValue().getSecondValue();
            final int sumPrice = requestData.getSecondValue();

            final RomanNumber romanNumber = 
                    localNumeralsRegistry.toRomanNumber(localNumber);
            
            // amount can not be zero because zero does not exist in Roman numeral system. 
            final int amount = 
                    romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            productCatalog.registry(productName, new PriceInCredits(Fraction.of(sumPrice,amount)));
            
            return Optional.empty();

        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return Optional.of(new Reply(e.getMessage()));
        }
        
    }

    /**
     * Extracts the variable part from the request.
     */
    private static Pair<Pair<LocalNumber, ProductName>, Integer> parse(final Request request){

        final Matcher matcher = REQUEST_PATTERN.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")),LocalNumberLiteral::of);

        final ProductName productName = 
                new ProductName(matcher.group(3));

        final int price = 
                Integer.valueOf(matcher.group(4));

        return Pair.of(Pair.of(new LocalNumber(literals), productName), price);
    }

    private static final Pattern REQUEST_PATTERN = 
            Pattern.compile("((\\w+ )+?)(\\w+) is (\\d+) Credits");

}
