package org.thoughtworks.assessment.merchant.processor.impl.handlers;

import java.util.Arrays;
import java.util.Optional;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;
import org.thoughtworks.assessment.merchant.processor.impl.handlers.ProductDefinitionRequestHandler;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.factory.RomanNumeralsConverterFactory;

public final class ProductDefinitionRequestReviserTest {

    @Test
    public void testProcess() throws Exception {

        final LocalNumeralsRegistry localNumberLiteralsRegistry = 
                EasyMock.mock(LocalNumeralsRegistry.class);

        final RomanNumber romanNumber = 
                RomanNumber.valueOf("II");
        
        final LocalNumber localNumber = 
                new LocalNumber(Arrays.asList(LocalNumberLiteral.of("pish"), LocalNumberLiteral.of("pish")));
        
        EasyMock.expect(localNumberLiteralsRegistry.toRomanNumber(localNumber)).andReturn(romanNumber);
        
        
        final ProductCatalog productCatalog = EasyMock.mock(ProductCatalog.class);;

        productCatalog.registry(new ProductName("Iron"),new PriceInCredits(Fraction.of(3910,2)));
        
        EasyMock.replay(localNumberLiteralsRegistry);
        EasyMock.replay(productCatalog);

        final ProductDefinitionRequestHandler priceRequestReviser = 
                new ProductDefinitionRequestHandler(localNumberLiteralsRegistry, RomanNumeralsConverterFactory.create(), productCatalog );
        
        final Optional<Reply> actual = 
                priceRequestReviser.process(new Request("pish pish Iron is 3910 Credits"));
        
        Assert.assertEquals(Optional.empty(), actual);
    }

}
