package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberSymbol;

public interface MetaStateFactory {

    public StateFactory getSymbolFactory(RomanNumberSymbol symbol);
}
