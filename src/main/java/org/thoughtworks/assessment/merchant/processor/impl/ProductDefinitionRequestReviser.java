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
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

public final class ProductDefinitionRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;
    private final ProductCatalog productCatalog;
    private final RomanNumeralsConverter romanNumeralsConverter;

    public ProductDefinitionRequestReviser(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter,
            final ProductCatalog productCatalog) {
        
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
        this.productCatalog = productCatalog;
        this.romanNumeralsConverter = romanNumeralsConverter;
    }

    @Override
    public Replay process(final Request request) {

        final Matcher matcher = IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final Pair<Pair<LocalNumber, ProductName>, Integer> parsedRequest = parseRequest(request,matcher);
        
        final LocalNumber localNumber = parsedRequest.getFirstValue().getFirstValue();
        final ProductName productName = parsedRequest.getFirstValue().getSecondValue();
        final int amount = parsedRequest.getSecondValue();

        try{
            
            final RomanNumber romanNumber = 
                    localNumberLiteralsRegistry.toRomanNumber(localNumber);
            
            final int sumPrice = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            productCatalog.addOrReplaceProduct(productName, new PriceInCredits(Fraction.of(sumPrice, amount)));
            
            return Replay.NONE;

        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return new Replay(e.getMessage());
        }
        
    }

    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue()).matches();
    }


    private static Pair<Pair<LocalNumber, ProductName>, Integer> parseRequest( 
            final Request request, final Matcher matcher){

        final Collection<LocalNumberLiteral> literals = new ArrayList<>();

        for( int i = 0; i < matcher.groupCount()-2; i+=2){

            literals.add(new LocalNumberLiteral(matcher.group(i)));
        }

        final ProductName productName = 
                new ProductName(matcher.group(matcher.groupCount()-2));

        final int price = 
                Integer.valueOf(matcher.group(matcher.groupCount()-1));

        return Pair.make(Pair.make(new LocalNumber(literals), productName), price);
    }

    private static final Pattern IS_PRODUCT_DEFINITION_REQUEST = 
            Pattern.compile("^((\\w+)\\s+)+is\\s+(\\d+)\\s+Credits$");


}
