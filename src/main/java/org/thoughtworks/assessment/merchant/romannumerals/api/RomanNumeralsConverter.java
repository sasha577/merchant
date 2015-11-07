package org.thoughtworks.assessment.merchant.romannumerals.api;

import org.thoughtworks.assessment.merchant.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.RomanNumber;

public interface RomanNumeralsConverter {

    public ArabicNumber toArabicNumber( RomanNumber number);
}
