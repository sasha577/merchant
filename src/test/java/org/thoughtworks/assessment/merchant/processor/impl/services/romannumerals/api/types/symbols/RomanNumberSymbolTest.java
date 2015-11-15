package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols.RomanNumberLiteral;

public final class RomanNumberSymbolTest {

    @Test
    public void testGetValue() {
        Assert.assertEquals(1, RomanNumberLiteral.I.getValue());
    }

    @Test
    public void testIsHigherThen() {
        Assert.assertTrue(RomanNumberLiteral.V.isHigherThen(RomanNumberLiteral.I));
    }

    @Test
    public void testIsHigherOrEqualThen() {
        Assert.assertTrue(RomanNumberLiteral.V.isHigherOrEqualThen(RomanNumberLiteral.I));
    }

    @Test
    public void testGetLowerValues() {
        Assert.assertTrue(RomanNumberLiteral.I.getLowerValues().isEmpty());
    }

    @Test
    public void testGetBy() {
        Assert.assertEquals(RomanNumberLiteral.V, RomanNumberLiteral.of('V'));
    }

}
