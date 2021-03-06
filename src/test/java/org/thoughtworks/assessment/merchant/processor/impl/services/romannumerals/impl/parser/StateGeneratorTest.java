package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.parser;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber.WrongRomanNumberException;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.factory.RomanNumeralsConverterFactory;


public final class StateGeneratorTest {

    @Test
    public void positive() throws WrongRomanNumberException {

        Assert.assertEquals( ArabicNumber.valueOf(1), EVALUATOR.toArabicNumber(RomanNumber.valueOf("I")));

        Assert.assertEquals( ArabicNumber.valueOf(10), EVALUATOR.toArabicNumber(RomanNumber.valueOf("X")));

        Assert.assertEquals( ArabicNumber.valueOf(40), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XL")));

        Assert.assertEquals( ArabicNumber.valueOf(20), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XX")));

        Assert.assertEquals( ArabicNumber.valueOf(3000), EVALUATOR.toArabicNumber(RomanNumber.valueOf("MMM")));

        Assert.assertEquals( ArabicNumber.valueOf(39), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XXXIX")));

        Assert.assertEquals( ArabicNumber.valueOf(44), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XLIV")));

        Assert.assertEquals( ArabicNumber.valueOf(99), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XCIX")));

        Assert.assertEquals( ArabicNumber.valueOf(94), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XCIV")));
        
        Assert.assertEquals( ArabicNumber.valueOf(1944), EVALUATOR.toArabicNumber(RomanNumber.valueOf("MCMXLIV")));
        
        Assert.assertEquals( ArabicNumber.valueOf(3888), EVALUATOR.toArabicNumber(RomanNumber.valueOf("MMMDCCCLXXXVIII")));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative0() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf(""));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative1() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("IIV"));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative2() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("DXXL"));

    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative3() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("VVVV"));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative5() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("VV"));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative6() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("XXXIXV"));
    }

    @Test(expected=WrongRomanNumberException.class)
    public void negative7() throws WrongRomanNumberException {

        EVALUATOR.toArabicNumber(RomanNumber.valueOf("XCVXI"));
    }


    private static final RomanNumeralsConverter EVALUATOR = 
    		RomanNumeralsConverterFactory.create();

}
