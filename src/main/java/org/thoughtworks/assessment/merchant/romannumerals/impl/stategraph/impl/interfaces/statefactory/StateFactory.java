package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory;

import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;

/**
 * Creates the graph states.
 *
 */
public interface StateFactory extends Function<Optional<RomanNumberLiteral>,State>{

    /**
     * Gets a Roman literal for witch this factory creates states.
     */
    public RomanNumberLiteral forLiteral();
    
}
