package org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.LocalNumberLiteralsRegistryImpl;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.literal.RomanNumberLiteral;

public final class LocalNumberLiteralsRegistryImplTest {

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
        
        numberLiteralsRegistry.toRomanNumber(localNumber);
        
    }

}
