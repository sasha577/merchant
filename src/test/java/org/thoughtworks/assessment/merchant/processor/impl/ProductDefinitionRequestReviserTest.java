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
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

public class ProductDefinitionRequestReviserTest {

    @Test
    public void testProcess() throws Exception {

        final LocalNumberLiteralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumberLiteralsRegistry.class);

        final RomanNumber romanNumber = 
                RomanNumber.valueOf("IV");
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("pish"), LocalNumberLiteral.of("pish")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(romanNumber);
        
        
        final ProductCatalog productCatalog = EasyMock.mock(ProductCatalog.class);;

        productCatalog.addOrReplaceProduct(new ProductName("Iron"),new PriceInCredits(Fraction.of(4,3910)));
        
        EasyMock.replay(localNumberLiteralsRegistry);
        EasyMock.replay(productCatalog);

        final ProductDefinitionRequestReviser priceRequestReviser = 
                new ProductDefinitionRequestReviser(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create(), productCatalog );
        
        final Optional<Replay> actual = 
                priceRequestReviser.process(new Request("pish pish Iron is 3910 Credits"));
        
        Assert.assertEquals(Optional.empty(), actual);
    }

}
