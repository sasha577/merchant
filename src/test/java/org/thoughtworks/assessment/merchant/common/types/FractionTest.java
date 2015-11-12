package org.thoughtworks.assessment.merchant.common.types;

import org.junit.Assert;
import org.junit.Test;

public class FractionTest {

    @Test
    public void testToInteger() {
        Assert.assertEquals(5, Fraction.of(15,3).toInteger());
    }

    @Test
    public void testAdd() {
        Assert.assertEquals(Fraction.of(48,9), Fraction.of(15,3).add(Fraction.of(1,3)));
    }

    @Test
    public void testSubtract() {
        Assert.assertEquals(Fraction.of(42,9), Fraction.of(15,3).subtract(Fraction.of(1,3)));
    }

    @Test
    public void testMultiply() {
        Assert.assertEquals(Fraction.of(15,9), Fraction.of(15,3).multiply(Fraction.of(1,3)));
    }

    @Test
    public void testDivide() {
        Assert.assertEquals(Fraction.of(45,3), Fraction.of(15,3).divide(Fraction.of(1,3)));
    }

}
