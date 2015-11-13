package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

/**
 * <p>MetaStateFactory interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface MetaStateFactory {

    /**
     * <p>getSymbolFactory.</p>
     *
     * @param symbol a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory} object.
     */
    public StateFactory getSymbolFactory(RomanNumberLiteral symbol);
}
