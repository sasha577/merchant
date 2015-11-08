package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.types.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.productcatalog.types.ProductName;

public final class ProductDefinitionRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;

    public ProductDefinitionRequestReviser(final LocalNumberLiteralsRegistry localNumberLiteralsRegistry) {
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
    }

    @Override
    public Replay process(final Request request) {

        final Matcher matcher = IS_PRODUCT_DEFINITION_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final Pair<Pair<LocalNumber, ProductName>, Integer> parsedRequest = parseRequest(request,matcher);

        //final RomanNumber romanNumber = localNumberLiteralsRegistry.toRomanNumber(parsedRequest.getFirstValue().getFirstValue());

        return Replay.NONE;

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
