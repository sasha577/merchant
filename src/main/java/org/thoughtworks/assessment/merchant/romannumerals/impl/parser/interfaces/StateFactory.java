package org.thoughtworks.assessment.merchant.romannumerals.impl.parser.interfaces;

import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.State;

public interface StateFactory extends Function<Optional<RomanNumberLiteral>,State>{

    public RomanNumberLiteral getSymbol();
}
