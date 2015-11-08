package org.thoughtworks.assessment.merchant.romannumerals.api;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;

public interface RomanNumeralsConverter {

    public ArabicNumber toArabicNumber( RomanNumber number)  throws WrongRomanNumberException;
}
