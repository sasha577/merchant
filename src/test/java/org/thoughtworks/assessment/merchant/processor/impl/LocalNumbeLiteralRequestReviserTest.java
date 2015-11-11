package org.thoughtworks.assessment.merchant.processor.impl;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

public final class LocalNumbeLiteralRequestReviserTest {


    //    @Rule
    //    public EasyMockRule rule = new EasyMockRule(this);
    //
    //    @Mock
    //    private ProductCatalog productCatalog;
    //    
    //    @Mock
    //    private LocalNumberLiteralsRegistry localNumberLiteralsRegistry;


    @Test
    public void testProcess() {

        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);
        
        localNumberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("bock"), RomanNumberLiteral.C);
        localNumberLiteralsRegistry.registerLocalLiteral(LocalNumberLiteral.of("dock"), RomanNumberLiteral.I);
        
        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumbeLiteralRequestReviser localNumbeLiteralRequestReviser = 
                new LocalNumbeLiteralRequestReviser(localNumberLiteralsRegistry);
        
        
        localNumbeLiteralRequestReviser.process(new Request("bock is C"));
        localNumbeLiteralRequestReviser.process(new Request("dock is I"));
        
    }

    @Test
    public void testMatches() {

        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumbeLiteralRequestReviser localNumbeLiteralRequestReviser = 
                new LocalNumbeLiteralRequestReviser(localNumberLiteralsRegistry);
        
        
        Assert.assertTrue(localNumbeLiteralRequestReviser.isResposibleFor(new Request("bock is C")));
    }

    @Test
    public void testNotMatches() {


        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = EasyMock.mock(LocalNumberLiteralsRegistry.class);
        
        final LocalNumbeLiteralRequestReviser localNumbeLiteralRequestReviser = 
                new LocalNumbeLiteralRequestReviser(localNumberLiteralsRegistry);
        
        EasyMock.replay(localNumberLiteralsRegistry);
        
        Assert.assertFalse(localNumbeLiteralRequestReviser.isResposibleFor(new Request("bock is not C")));
    }

}
