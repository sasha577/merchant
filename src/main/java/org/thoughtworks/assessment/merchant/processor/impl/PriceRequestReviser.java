package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

public final class PriceRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;
    private final ProductCatalog productCatalog;
    private final RomanNumeralsConverter romanNumeralsConverter;

    public PriceRequestReviser(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry, 
            final ProductCatalog productCatalog,
            final RomanNumeralsConverter romanNumeralsConverter) {
        
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
        this.productCatalog = productCatalog;
        this.romanNumeralsConverter = romanNumeralsConverter;
    }

    @Override
    public Replay process(final Request request) {

        final Matcher matcher = IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final Pair<LocalNumber, ProductName> parsedRequest = parseRequest(request,matcher);
        
        final LocalNumber localNumber = parsedRequest.getFirstValue();
        final ProductName productName = parsedRequest.getSecondValue();

        try{
            
            final RomanNumber romanNumber = 
                    localNumberLiteralsRegistry.toRomanNumber(localNumber);
            
            final int count = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            final PriceInCredits productPrice = productCatalog.getPrice(productName);
            
            final int result = productPrice.getValue().multiply(Fraction.of(count)).toInteger();
            
            return new Replay(String.format("%s is %d Credits", localNumber.toLiteral(), result));

        }catch(final UnknownLiteral|WrongRomanNumberException|NotDefinedProductException e){
            
            return new Replay(e.getMessage());
        }
    }

    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue()).matches();
    }


    private static Pair<LocalNumber, ProductName> parseRequest( 
            final Request request, final Matcher matcher){

        final Collection<LocalNumberLiteral> literals = new ArrayList<>();

        for( int i = 0; i < matcher.groupCount()-1; ++i){

            literals.add(new LocalNumberLiteral(matcher.group(i)));
        }

        final ProductName productName = 
                new ProductName(matcher.group(matcher.groupCount()-1));


        return Pair.make(new LocalNumber(literals), productName);
    }

    private static final Pattern IS_PRODUCT_DEFINITION_REQUEST = 
            Pattern.compile("^how\\s+many\\s+Credits\\s+is(\\s+(\\w+))+\\s*?$");


}
