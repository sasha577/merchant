package org.thoughtworks.assessment.merchant.numberregistry.api;

import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * <p>LocalNumberLiteralsRegistry interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface LocalNumberLiteralsRegistry {

    /**
     * <p>registerLocalLiteral.</p>
     *
     * @param localLiteral a {@link org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral} object.
     * @param romanLiteral a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     */
    public void registerLocalLiteral(LocalNumberLiteral localLiteral, RomanNumberLiteral romanLiteral);

    /**
     * <p>toRomanNumber.</p>
     *
     * @param localNumber a {@link org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber} object.
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber} object.
     * @throws org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral if any.
     */
    public RomanNumber toRomanNumber(LocalNumber localNumber) throws UnknownLiteral;

}
