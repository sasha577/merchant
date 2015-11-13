package org.thoughtworks.assessment.merchant.numberregistry.api;

import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * Keeps the mapping between the local numerals and the Roman numerals.
 */
public interface LocalNumeralsRegistry {

    /**
     * Registers the local number.
     *
     * @param localLiteral a local number literal to register.
     * @param romanLiteral a Roman number the local number literal will be associated to.
     */
    public void registerLocalLiteral(LocalNumberLiteral localLiteral, RomanNumberLiteral romanLiteral);

    /**
     * Converts the local number to the Roman number.
     *
     * @param localNumber a local number to convert.
     * @return a Roman number.
     * @throws UnknownLiteral if localNumber contains a literal have not been registered before.
     */
    public RomanNumber toRomanNumber(LocalNumber localNumber) throws UnknownLiteral;

}
