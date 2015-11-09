package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

public interface MetaStateFactory {

    public StateFactory getSymbolFactory(RomanNumberLiteral symbol);
}
