package org.thoughtworks.assessment.merchant.numberregistry;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

public class LocalNumberLiteralsRegistryImplTest {

    @Test
    public void registerLocalLiteral() throws Exception {
        
        final LocalNumberLiteralsRegistryImpl numberLiteralsRegistry = 
                new LocalNumberLiteralsRegistryImpl();
        
        numberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("pink"), RomanNumberLiteral.C);
        numberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("pong"), RomanNumberLiteral.I);
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("pink"),LocalNumberLiteral.of("pong")));
        
        final RomanNumber actual = numberLiteralsRegistry.toRomanNumber(localNumber);
        
        Assert.assertEquals(RomanNumber.valueOf("CI"), actual);

    }

    @Test(expected=UnknownLiteral.class)
    public void getUnregisted() throws Exception {
        
        final LocalNumberLiteralsRegistryImpl numberLiteralsRegistry = 
                new LocalNumberLiteralsRegistryImpl();
        
        numberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("pink"), RomanNumberLiteral.C);
        numberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("pong"), RomanNumberLiteral.I);
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("pink"),LocalNumberLiteral.of("pong1")));
        
        final RomanNumber actual = numberLiteralsRegistry.toRomanNumber(localNumber);
        
    }

}
