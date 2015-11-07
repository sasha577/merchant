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

        Assert.assertEquals( ArabicNumber.valueOf(10), EVALUATOR.evaluate(RomanNumber.valueOf("X")));

        Assert.assertEquals( ArabicNumber.valueOf(40), EVALUATOR.evaluate(RomanNumber.valueOf("XL")));

        Assert.assertEquals( ArabicNumber.valueOf(20), EVALUATOR.evaluate(RomanNumber.valueOf("XX")));

        Assert.assertEquals( ArabicNumber.valueOf(30), EVALUATOR.evaluate(RomanNumber.valueOf("XXX")));

        Assert.assertEquals( ArabicNumber.valueOf(3), EVALUATOR.evaluate(RomanNumber.valueOf("III")));

        Assert.assertEquals( ArabicNumber.valueOf(3000), EVALUATOR.evaluate(RomanNumber.valueOf("MMM")));

        Assert.assertEquals( ArabicNumber.valueOf(39), EVALUATOR.evaluate(RomanNumber.valueOf("XXXIX")));

        Assert.assertEquals( ArabicNumber.valueOf(44), EVALUATOR.evaluate(RomanNumber.valueOf("XLIV")));

        Assert.assertEquals( ArabicNumber.valueOf(99), EVALUATOR.evaluate(RomanNumber.valueOf("XCIX")));

        Assert.assertEquals( ArabicNumber.valueOf(94), EVALUATOR.evaluate(RomanNumber.valueOf("XCIV")));
    }

    @Test(expected=WrongNumberException.class)
    public void negative1() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("IIV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative2() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("DXXL"));

    }

    @Test(expected=WrongNumberException.class)
    public void negative3() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("VVVV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative5() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("VV"));
    }

    @Test(expected=WrongNumberException.class)
    public void negative6() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("XXXIXV"));
    }
    
    @Test(expected=WrongNumberException.class)
    public void negative7() throws WrongNumberException {

        EVALUATOR.evaluate(RomanNumber.valueOf("XCVXI"));
    }


    private static final Evaluator EVALUATOR = new StateGenerator().generate();

}
