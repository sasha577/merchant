package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory;

import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;

public interface StateFactory extends Function<Optional<RomanNumberLiteral>,State>{

    public RomanNumberLiteral getSymbol();
}