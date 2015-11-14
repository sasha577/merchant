package org.thoughtworks.assessment.merchant.productcatalog;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;

public final class ProductCatalogImplTest {

    @Test
    public void addOrReplaceProduct() throws Exception {
        
        final ProductCatalogImpl productCatalog = new ProductCatalogImpl();
        
        productCatalog.registry(new ProductName("pn"), new PriceInCredits(Fraction.of(1, 2)));
        
        final PriceInCredits actual = productCatalog.getPrice(new ProductName("pn"));
        
        Assert.assertEquals(new PriceInCredits(Fraction.of(1, 2)), actual);
    }

    @Test(expected=NotDefinedProductException.class)
    public void getPriceForUnregistered() throws Exception {
        
        final ProductCatalogImpl productCatalog = new ProductCatalogImpl();
        
        productCatalog.registry(new ProductName("pn"), new PriceInCredits(Fraction.of(1, 2)));
        
        productCatalog.getPrice(new ProductName("pn1"));
        
        
    }


}
