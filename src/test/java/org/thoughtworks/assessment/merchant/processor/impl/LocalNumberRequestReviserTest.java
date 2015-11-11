package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

public final class LocalNumberRequestReviserTest {

    @Test
    public void testProcess() throws Exception {
        
        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);
        
        final RomanNumber romanNumber = 
                RomanNumber.valueOf("IV");
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("bock"), LocalNumberLiteral.of("dock")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(romanNumber);
        
        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumberRequestReviser localNumberRequestReviser = 
                new LocalNumberRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create());
        
        final Optional<Replay> actual = localNumberRequestReviser.process(new Request("how much is bock dock ?"));
        
        Assert.assertEquals(Optional.of(new Replay("bock dock is 4")), actual);
    }

    @Test
    public void testProcessInvalidNumber() throws Exception {
        
        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);
        
        final RomanNumber romanNumber = 
                RomanNumber.valueOf("VV");
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("bock"), LocalNumberLiteral.of("bock")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(romanNumber);
        
        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumberRequestReviser localNumberRequestReviser = 
                new LocalNumberRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create());
        
        final Optional<Replay> actual = localNumberRequestReviser.process(new Request("how much is bock bock ?"));
        
        Assert.assertEquals(Optional.of(new Replay("wrong roman number: VV")), actual);
    }

    @Test
    public void testProcessUnknownLiteral() throws Exception {
        
        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("bock"), LocalNumberLiteral.of("dockk")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andThrow(new UnknownLiteral(LocalNumberLiteral.of("dockk")));
        
        EasyMock.replay(localNumberLiteralsRegistry);

        final LocalNumberRequestReviser localNumberRequestReviser = 
                new LocalNumberRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create());
        
        final Optional<Replay> actual = localNumberRequestReviser.process(new Request("how much is bock dockk ?"));
        
        Assert.assertEquals(Optional.of(new Replay("unknown literal: dockk")), actual);
    }

    @Test
    public void testMatches() {
        
        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        final RomanNumeralsConverter romanNumeralsConverter = 
                EasyMock.mock(RomanNumeralsConverter.class);
        
        final LocalNumberRequestReviser localNumberRequestReviser = 
                new LocalNumberRequestReviser(localNumberLiteralsRegistry, romanNumeralsConverter);
        
        Assert.assertTrue(localNumberRequestReviser.isResposibleFor(new Request("how much is bock dock ?")));
    }

    @Test
    public void testDoesNotMatch() {
        
        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        final RomanNumeralsConverter romanNumeralsConverter = 
                EasyMock.mock(RomanNumeralsConverter.class);
        
        final LocalNumberRequestReviser localNumberRequestReviser = 
                new LocalNumberRequestReviser(localNumberLiteralsRegistry, romanNumeralsConverter);
        
        Assert.assertFalse(localNumberRequestReviser.isResposibleFor(new Request("what is is bock dock ?")));
    }
}
