package org.thoughtworks.assessment.merchant.processor.impl;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

public final class LocalNumbeLiteralRequestReviserTest {

    @Test
    public void testProcess() {

        final LocalNumeralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumeralsRegistry.class);
        
        localNumberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("bock"), RomanNumberLiteral.C);
        localNumberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("dock"), RomanNumberLiteral.I);

        
        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumeralDefinitionRequestHandler localNumbeLiteralRequestReviser = 
                new LocalNumeralDefinitionRequestHandler(localNumberLiteralsRegistry);
        
        
        localNumbeLiteralRequestReviser.process(new Request("bock is C"));
        localNumbeLiteralRequestReviser.process(new Request("dock is I"));
        
    }

    @Test
    public void testMatches() {

        final LocalNumeralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumeralsRegistry.class);

        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumeralDefinitionRequestHandler localNumbeLiteralRequestReviser = 
                new LocalNumeralDefinitionRequestHandler(localNumberLiteralsRegistry);
        
        
        Assert.assertTrue(localNumbeLiteralRequestReviser.isResposibleFor(new Request("bock is C")));
    }

    @Test
    public void testNotMatches() {


        final LocalNumeralsRegistry localNumberLiteralsRegistry = EasyMock.mock(LocalNumeralsRegistry.class);
        
        final LocalNumeralDefinitionRequestHandler localNumbeLiteralRequestReviser = 
                new LocalNumeralDefinitionRequestHandler(localNumberLiteralsRegistry);
        
        EasyMock.replay(localNumberLiteralsRegistry);
        
        Assert.assertFalse(localNumbeLiteralRequestReviser.isResposibleFor(new Request("bock is not C")));
    }

}
