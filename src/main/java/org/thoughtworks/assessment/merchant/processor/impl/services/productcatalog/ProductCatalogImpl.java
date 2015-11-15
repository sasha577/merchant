package org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog;

import java.util.HashMap;
import java.util.Map;

import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName;
import org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types.ProductName.NotDefinedProductException;

/**
 * The product catalog.
 * Let registry the products and get information about they prices.
 */
public final class ProductCatalogImpl implements ProductCatalog{

    private final Map<ProductName,PriceInCredits>  priceByName;
    
    /**
     * <p>Constructor for ProductCatalogImpl.</p>
     */
    public ProductCatalogImpl() {

        this.priceByName = new HashMap<ProductName,PriceInCredits>();
    }
    
    /** {@inheritDoc} */
    @Override
    public void registry(final ProductName product, final PriceInCredits price){
        priceByName.put(product, price);
    }
    
    /** {@inheritDoc} */
    @Override
    public PriceInCredits getPrice(final ProductName product) throws NotDefinedProductException{
        
        assert product!=null: "prduct is null";
        
        final PriceInCredits priceInCredits = priceByName.get(product);
        
        assertProductDefined((priceInCredits!=null),product);
        
        return priceInCredits;
    }

    /**
     * throws an exception if product was not found
     */
    private static void assertProductDefined(final boolean found, final ProductName productName) throws NotDefinedProductException{
        if(!found){
            throw new NotDefinedProductException(productName);
        }
    }
    

}
