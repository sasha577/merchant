package org.thoughtworks.assessment.merchant.productcatalog.api;

import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;

public interface ProductCatalog {

    public void addOrReplaceProduct(final ProductName product, final PriceInCredits price);
    
    public PriceInCredits getPrice(final ProductName product) throws NotDefinedProductException;

}
