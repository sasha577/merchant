package org.thoughtworks.assessment.merchant.romannumerals.api;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

/**
 * <p>RomanNumeralsConverter interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface RomanNumeralsConverter {

    /**
     * <p>toArabicNumber.</p>
     *
     * @param number a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber} object.
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber} object.
     * @throws org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException if any.
     */
    public ArabicNumber toArabicNumber( RomanNumber number)  throws WrongRomanNumberException;
}
