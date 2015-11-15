package org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api;

import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName.NotDefinedProductException;

/**
 * The product catalog.
 * Let registry the products and get information about they prices.
 */
public interface ProductCatalog {

    /**
     * Registers a product.
     * 
     * When registered more times, the last registration overrides the previous one. 
     *
     * @param product a product to register.
     * @param price a price of the given product.
     */
    public void registry(final ProductName product, final PriceInCredits price);
    
    /**
     * Gets price of the product.
     *
     * @param product a product in question.
     * @return a price of the product.
     * @throws NotDefinedProductException if product has not been registered before.
     */
    public PriceInCredits getPrice(final ProductName product) throws NotDefinedProductException;

}
