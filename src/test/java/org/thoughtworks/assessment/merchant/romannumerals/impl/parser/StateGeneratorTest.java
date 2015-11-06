package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.Evaluator.WrongNumberException;


public final class StateGeneratorTest {

    @Test
    public void positive() throws WrongNumberException {
        
        System.out.print(State.COUNTER);

        Assert.assertEquals( ArabicNumber.valueOf(10), EVALUATOR.evaluate(RomanNumber.parse("X")));

        Assert.assertEquals( ArabicNumber.valueOf(40), EVALUATOR.evaluate(RomanNumber.parse("XL")));

        Assert.assertEquals( ArabicNumber.valueOf(20), EVALUATOR.evaluate(RomanNumber.parse("XX")));

        Assert.assertEquals( ArabicNumber.valueOf(30), EVALUATOR.evaluate(RomanNumber.parse("XXX")));

        Assert.assertEquals( ArabicNumber.valueOf(3), EVALUATOR.evaluate(RomanNumber.parse("III")));

        Assert.assertEquals( ArabicNumber.valueOf(3000), EVALUATOR.evaluate(RomanNumber.parse("MMM")));

        Assert.assertEquals( ArabicNumber.valueOf(39), EVALUATOR.evaluate(RomanNumber.parse("XXXIX")));

        Assert.assertEquals( ArabicNumber.valueOf(44), EVALUATOR.evaluate(RomanNumber.parse("XLIV")));

        Assert.assertEquals( ArabicNumber.valueOf(99), EVALUATOR.evaluate(RomanNumber.parse("XCIX")));

    }

    @Test(expected=WrongNumberException.class)
    public void negative1() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.parse("IIV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative2() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.parse("DXXL"));

    }

    @Test(expected=WrongNumberException.class)
    public void negative3() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.parse("VVVV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative5() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.parse("VV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative6() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.parse("XXXIXV"));
    }

    private static final Evaluator EVALUATOR = new StateGenerator().generate();

}
