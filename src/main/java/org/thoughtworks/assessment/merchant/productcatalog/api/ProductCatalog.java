package org.thoughtworks.assessment.merchant.productcatalog.api;

import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;

/**
 * <p>ProductCatalog interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface ProductCatalog {

    /**
     * <p>addOrReplaceProduct.</p>
     *
     * @param product a {@link org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName} object.
     * @param price a {@link org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits} object.
     */
    public void addOrReplaceProduct(final ProductName product, final PriceInCredits price);
    
    /**
     * <p>getPrice.</p>
     *
     * @param product a {@link org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName} object.
     * @return a {@link org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits} object.
     * @throws org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException if any.
     */
    public PriceInCredits getPrice(final ProductName product) throws NotDefinedProductException;

}
