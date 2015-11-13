package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestHandler;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * <p>LocalNumbeLiteralRequestReviser class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class LocalNumeralDefinitionRequestHandler implements RequestHandler{

    private final LocalNumeralsRegistry localNumeralsRegistry;

    /**
     * <p>Constructor for LocalNumbeLiteralRequestReviser.</p>
     *
     * @param localNumeralsRegistry a {@link org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry} object.
     */
    public LocalNumeralDefinitionRequestHandler(final LocalNumeralsRegistry localNumeralsRegistry) {
        this.localNumeralsRegistry = localNumeralsRegistry;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Reply> process(final Request request) {

        final Matcher matcher = IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final LocalNumberLiteral localLiteral = LocalNumberLiteral.of(matcher.group(1));
        final char literal = matcher.group(2).charAt(0);

        try{

            final RomanNumberLiteral romanLiteral = RomanNumberLiteral.getBy(literal);

            localNumeralsRegistry.registerLocalLiteral(localLiteral , romanLiteral);

            return Optional.empty();

        }catch(final NoSuchElementException e){

            return Optional.of(new Reply(String.format("literal '%s' is not valid roman number.", literal)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue()).matches();
    }

    private static final Pattern IS_LOCAL_NUMBER_REQUEST = Pattern.compile("(\\w+) is (\\w)$");
}
