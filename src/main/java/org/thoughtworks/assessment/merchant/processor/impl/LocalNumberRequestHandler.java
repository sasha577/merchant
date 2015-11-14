package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestHandler;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

/**
 * The handle for the requests asking for the value of the local number.
 * For example: 'how much is bock dock ?'. 
 */
public final class LocalNumberRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumeralsRegistry;
    private final RomanNumeralsConverter romanNumeralsConverter;

    /**
     * Constructor.
     *
     * @param localNumeralsRegistry a registry for local numerals.
     * @param romanNumeralsConverter a converter from Roman to Arabic numerals.
     */
    public LocalNumberRequestHandler(
            final LocalNumeralsRegistry localNumeralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter) {
        
        this.localNumeralsRegistry = localNumeralsRegistry;
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

            final LocalNumber localNumber = parse(request);
            
            final RomanNumber romanNumber = 
                    localNumeralsRegistry.toRomanNumber(localNumber);
            
            final int result = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            return Optional.of(new Reply(String.format("%s is %d", localNumber.toLiteral(), result)));
            
        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return Optional.of(new Reply(e.getMessage()));
            
        }
    }

    /**
     * Extracts the variable part from the request.
     */
    private static LocalNumber parse(final Request request) {
        
        final Matcher matcher = REQUEST_PATTERN.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")), LocalNumberLiteral::of);
        
        return new LocalNumber(literals);
    }

    private static final Pattern REQUEST_PATTERN = 
            Pattern.compile("how much is ((\\w+ )+)\\?");

}
