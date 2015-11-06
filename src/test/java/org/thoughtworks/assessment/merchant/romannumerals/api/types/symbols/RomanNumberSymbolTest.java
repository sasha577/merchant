package org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols;

import org.junit.Assert;
import org.junit.Test;

public final class RomanNumberSymbolTest {

    @Test
    public void testGetValue() {
        Assert.assertEquals(1, RomanNumberSymbol.I.getValue());
    }

    @Test
    public void testIsHigherThen() {
        Assert.assertTrue(RomanNumberSymbol.V.isHigherThen(RomanNumberSymbol.I));
    }

    @Test
    public void testIsHigherOrEqualThen() {
        Assert.assertTrue(RomanNumberSymbol.V.isHigherOrEqualThen(RomanNumberSymbol.I));
    }

    @Test
    public void testGetLowerValues() {
        Assert.assertTrue(RomanNumberSymbol.I.getLowerValues().isEmpty());
    }

    @Test
    public void testGetBy() {
        Assert.assertEquals(RomanNumberSymbol.V, RomanNumberSymbol.getBy('V'));
    }

}
