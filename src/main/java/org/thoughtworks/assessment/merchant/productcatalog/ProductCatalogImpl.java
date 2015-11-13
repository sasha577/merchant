package org.thoughtworks.assessment.merchant.productcatalog;

import java.util.HashMap;
import java.util.Map;

import org.thoughtworks.assessment.merchant.productcatalog.api.ProductCatalog;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;
import org.thoughtworks.assessment.merchant.productcatalog.api.exceptions.NotDefinedProductException;

/**
 * <p>ProductCatalogImpl class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
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
    public void addOrReplaceProduct(final ProductName product, final PriceInCredits price){
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

    private static void assertProductDefined(final boolean found, final ProductName productName) throws NotDefinedProductException{
        if(!found){
            throw new NotDefinedProductException(productName);
        }
    }
    

}
