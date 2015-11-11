package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

public final class LocalNumberRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;
    private final RomanNumeralsConverter romanNumeralsConverter;

    public LocalNumberRequestReviser(
            final LocalNumberLiteralsRegistry localNumberLiteralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter) {
        
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
        this.romanNumeralsConverter = romanNumeralsConverter;
    }

    @Override
    public Optional<Replay> process(final Request request) {

        final Matcher matcher = HOW_MUCH_IS_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final LocalNumber localNumber = parseRequest(request,matcher);

        try{
            
            final RomanNumber romanNumber = 
                    localNumberLiteralsRegistry.toRomanNumber(localNumber);
            
            final int result = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            return Optional.of(new Replay(String.format("%s is %d", localNumber.toLiteral(), result)));
            
        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return Optional.of(new Replay(e.getMessage()));
            
        }
    }

    @Override
    public boolean isResposibleFor(final Request request) {

        return HOW_MUCH_IS_REQUEST.matcher(request.getValue()).matches();
    }


    private static LocalNumber parseRequest( 
            final Request request, final Matcher matcher){

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")),LocalNumberLiteral::of);
        
        return new LocalNumber(literals);
    }

    private static final Pattern HOW_MUCH_IS_REQUEST = 
            Pattern.compile("how much is ((\\w+ )+)\\?$");


}
