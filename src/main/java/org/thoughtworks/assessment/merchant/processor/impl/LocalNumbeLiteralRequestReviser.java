package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.types.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

public final class LocalNumbeLiteralRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;
    
    public LocalNumbeLiteralRequestReviser(final LocalNumberLiteralsRegistry localNumberLiteralsRegistry) {
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
    }

    @Override
    public Replay process(final Request request) {
        
        final Matcher matcher = IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue());
        
        final boolean matches = matcher.matches();
        
        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";
        
        final LocalNumberLiteral localLiteral = new LocalNumberLiteral(matcher.group(1));
        
        final RomanNumberLiteral romanLiteral = RomanNumberLiteral.getBy(matcher.group(1).charAt(0));
        
        // TODO Auto-generated method stub
        localNumberLiteralsRegistry.registerLocalLiteral(localLiteral , romanLiteral);
        
        return Replay.NONE;
    }

    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue()).matches();
    }

    private static final Pattern IS_LOCAL_NUMBER_REQUEST = 
            Pattern.compile(
                    String.format("^(\\w+) is (%s)$", 
                            Arrays.asList(RomanNumberLiteral.values()).stream().map(p -> p.name()).collect(Collectors.joining("|"))));
}
