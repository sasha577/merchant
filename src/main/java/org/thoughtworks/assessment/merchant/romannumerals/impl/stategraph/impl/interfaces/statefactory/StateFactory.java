package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory;

import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;

/**
 * <p>StateFactory interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface StateFactory extends Function<Optional<RomanNumberLiteral>,State>{

    /**
     * <p>getSymbol.</p>
     *
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     */
    public RomanNumberLiteral getSymbol();
}
