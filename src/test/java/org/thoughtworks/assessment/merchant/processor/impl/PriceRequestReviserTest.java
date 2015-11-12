package org.thoughtworks.assessment.merchant.processor.impl;

import java.util.Arrays;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

public class PriceRequestReviserTest {

    @Test
    public void testProcess() throws Exception {

        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        final RomanNumber romanNumber = 
                RomanNumber.valueOf("IV");
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("glob"), LocalNumberLiteral.of("prok")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(romanNumber);
        
        
        final ProductCatalog productCatalog = EasyMock.mock(ProductCatalog.class);;

        EasyMock.expect(productCatalog.getPrice(new ProductName("Silver"))).andReturn(new PriceInCredits(Fraction.of(10)));
        
        EasyMock.replay(localNumberLiteralsRegistry);
        EasyMock.replay(productCatalog);

        final PriceRequestReviser priceRequestReviser = 
                new PriceRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create(), productCatalog );
        
        final Optional<Replay> actual = 
                priceRequestReviser.process(new Request("how many Credits is glob prok Silver ?"));
        
        Assert.assertEquals(Optional.of(new Replay("glob prok Silver is 40 Credits")), actual);
    }

    @Test
    public void testProcessUnknowProduct() throws Exception {

        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("glob"), LocalNumberLiteral.of("prok")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(RomanNumber.valueOf("IV"));
        
        
        final ProductCatalog productCatalog = EasyMock.mock(ProductCatalog.class);;

        EasyMock.expect(productCatalog.getPrice(new ProductName("Silver"))).andThrow(new NotDefinedProductException(new ProductName("Silver")));
        
        EasyMock.replay(localNumberLiteralsRegistry);
        EasyMock.replay(productCatalog);

        final PriceRequestReviser priceRequestReviser = 
                new PriceRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create(), productCatalog );
        
        final Optional<Replay> actual = 
                priceRequestReviser.process(new Request("how many Credits is glob prok Silver ?"));
        
        Assert.assertEquals(Optional.of(new Replay("product Silver is not defined")), actual);
    }

}
