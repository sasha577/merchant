package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.base.RequestReviser;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * <p>LocalNumbeLiteralRequestReviser class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class LocalNumbeLiteralRequestReviser implements RequestReviser{

    private final LocalNumberLiteralsRegistry localNumberLiteralsRegistry;

    /**
     * <p>Constructor for LocalNumbeLiteralRequestReviser.</p>
     *
     * @param localNumberLiteralsRegistry a {@link org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry} object.
     */
    public LocalNumbeLiteralRequestReviser(final LocalNumberLiteralsRegistry localNumberLiteralsRegistry) {
        this.localNumberLiteralsRegistry = localNumberLiteralsRegistry;
    }

    /** {@inheritDoc} */
    @Override
    public Optional<Replay> process(final Request request) {

        final Matcher matcher = IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue());

        final boolean matches = matcher.matches();

        assert matches: "programmer error: matching must have been checked in isResposibleFor before!";

        final LocalNumberLiteral localLiteral = LocalNumberLiteral.of(matcher.group(1));
        final char literal = matcher.group(2).charAt(0);

        try{

            final RomanNumberLiteral romanLiteral = RomanNumberLiteral.getBy(literal);

            localNumberLiteralsRegistry.registerLocalLiteral(localLiteral , romanLiteral);

            return Optional.empty();

        }catch(final NoSuchElementException e){

            return Optional.of(new Replay(String.format("literal '%s' is not valid roman number.", literal)));
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isResposibleFor(final Request request) {

        return IS_LOCAL_NUMBER_REQUEST.matcher(request.getValue()).matches();
    }

    private static final Pattern IS_LOCAL_NUMBER_REQUEST = Pattern.compile("(\\w+) is (\\w)$");
}
