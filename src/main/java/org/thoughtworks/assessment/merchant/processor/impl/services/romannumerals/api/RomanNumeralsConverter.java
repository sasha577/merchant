package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api;

import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber.WrongRomanNumberException;

/**
 * Converters the Roman numeral into the Arabic numeral.
 */
public interface RomanNumeralsConverter {

    /**
     * Converters the Roman numeral into the Arabic numeral.
     *
     * @param number a Roman numeral to be converted to the Arabic numeral.
     * @return a Arabic numeral.
     * @throws WrongRomanNumberException if the given Roman number is not valid.
     */
    public ArabicNumber toArabicNumber( RomanNumber number)  throws WrongRomanNumberException;
}
