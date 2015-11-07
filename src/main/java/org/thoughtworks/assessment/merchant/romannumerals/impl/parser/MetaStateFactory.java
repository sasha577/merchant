package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

public interface MetaStateFactory {

    public StateFactory getSymbolFactory(RomanNumberLiteral symbol);
}
