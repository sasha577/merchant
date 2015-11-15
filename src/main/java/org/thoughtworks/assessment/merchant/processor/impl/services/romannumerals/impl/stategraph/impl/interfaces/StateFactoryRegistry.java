package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.impl.interfaces;

import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

/**
 * Provides StateFactory for particular Roman literal.
 */
public interface StateFactoryRegistry {

    /**
     * Provides StateFactory for particular Roman literal.
     */
    public StateFactory getStateFactoryForLiteral(RomanNumberLiteral symbol);
}
