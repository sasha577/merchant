package org.thoughtworks.assessment.merchant.processor.impl.handlers;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.base.RequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.literal.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.literal.RomanNumberLiteral.WrongRomanLiteral;

/**
 * The handle for the requests defining the value of local number literals.
 * For example: 'prok is V'. 
 */
public final class LocalNumeralDefinitionRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumeralsRegistry;

    /**
     * Constructor.
     *
     * @param localNumeralsRegistry a registry for local numerals.
     */
    public LocalNumeralDefinitionRequestHandler(final LocalNumeralsRegistry localNumeralsRegistry) {
        this.localNumeralsRegistry = localNumeralsRegistry;
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

            final Pair<LocalNumberLiteral,RomanNumberLiteral> requestData = parse(request); 

            localNumeralsRegistry.registerLocalLiteral(requestData.getFirstValue() , requestData.getSecondValue());

            return Optional.empty();

        }catch(final WrongRomanLiteral e){

            return Optional.of(new Reply(String.format("literal '%s' is not valid roman number.", e.getWrongLiteral())));
        }
    }

    /**
     * Extracts the variable part from the request.
     */
    private static Pair<LocalNumberLiteral, RomanNumberLiteral> parse(final Request request) {
        
        final Matcher matcher = REQUEST_PATTERN.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final LocalNumberLiteral localLiteral = LocalNumberLiteral.of(matcher.group(1));
        final RomanNumberLiteral romanLiteral = RomanNumberLiteral.of(matcher.group(2).charAt(0));

        return Pair.of(localLiteral, romanLiteral);
    }

    private static final Pattern REQUEST_PATTERN = Pattern.compile("(\\w+) is (\\w)$");
}
