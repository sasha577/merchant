package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;
import org.thoughtworks.assessment.merchant.romannumerals.impl.RomanNumeralsConverterImpl;


public final class StateGeneratorTest {

    @Test
    public void positive() throws WrongRomanNumberException {

        Assert.assertEquals( ArabicNumber.valueOf(10), EVALUATOR.toArabicNumber(RomanNumber.valueOf("X")));

        Assert.assertEquals( ArabicNumber.valueOf(40), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XL")));

        Assert.assertEquals( ArabicNumber.valueOf(20), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XX")));

        Assert.assertEquals( ArabicNumber.valueOf(30), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XXX")));

        Assert.assertEquals( ArabicNumber.valueOf(3), EVALUATOR.toArabicNumber(RomanNumber.valueOf("III")));

        Assert.assertEquals( ArabicNumber.valueOf(3000), EVALUATOR.toArabicNumber(RomanNumber.valueOf("MMM")));

        Assert.assertEquals( ArabicNumber.valueOf(39), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XXXIX")));

        Assert.assertEquals( ArabicNumber.valueOf(44), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XLIV")));

        Assert.assertEquals( ArabicNumber.valueOf(99), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XCIX")));

        Assert.assertEquals( ArabicNumber.valueOf(94), EVALUATOR.toArabicNumber(RomanNumber.valueOf("XCIV")));
        
        Assert.assertEquals( ArabicNumber.valueOf(1944), EVALUATOR.toArabicNumber(RomanNumber.valueOf("MCMXLIV")));
        
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


    private static final RomanNumeralsConverterImpl EVALUATOR = createEvaluater();


    private static RomanNumeralsConverterImpl createEvaluater() {
        final RomanNumeralsConverterImpl result = new RomanNumeralsConverterFactory().create();
        System.out.print("number of states created: "+State.COUNTER);
        return result;
    }

}
