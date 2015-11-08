package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.interfaces.StateFactory;

public interface MetaStateFactory {

    public StateFactory getSymbolFactory(RomanNumberLiteral symbol);
}
