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
 * <p>LocalNumberRequestReviser class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class LocalNumberRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumberLiteralsRegistry;
    private final RomanNumeralsConverter romanNumeralsConverter;

    /**
     * <p>Constructor for LocalNumberRequestReviser.</p>
     *
     * @param localNumberLiteralsRegistry a {@link org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry} object.
     * @param romanNumeralsConverter a {@link org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter} object.
     */
    public LocalNumberRequestHandler(
            final LocalNumeralsRegistry localNumberLiteralsRegistry, 
            final RomanNumeralsConverter romanNumeralsConverter) {
        
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
        this.romanNumeralsConverter = romanNumeralsConverter;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Reply> process(final Request request) {

        final Matcher matcher = HOW_MUCH_IS_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final LocalNumber localNumber = parseRequest(request,matcher);

        try{
            
            final RomanNumber romanNumber = 
                    localNumberLiteralsRegistry.toRomanNumber(localNumber);
            
            final int result = romanNumeralsConverter.toArabicNumber(romanNumber).getValue();
            
            return Optional.of(new Reply(String.format("%s is %d", localNumber.toLiteral(), result)));
            
        }catch(final UnknownLiteral|WrongRomanNumberException e){
            
            return Optional.of(new Reply(e.getMessage()));
            
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isResposibleFor(final Request request) {

        return HOW_MUCH_IS_REQUEST.matcher(request.getValue()).matches();
    }

    private static LocalNumber parseRequest( 
            final Request request, final Matcher matcher){

        final List<LocalNumberLiteral> literals = 
                CollectionUtils.map(Arrays.asList(matcher.group(1).split("\\s+")), LocalNumberLiteral::of);
        
        return new LocalNumber(literals);
    }

    private static final Pattern HOW_MUCH_IS_REQUEST = 
            Pattern.compile("how much is ((\\w+ )+)\\?$");


}
