package org.thoughtworks.assessment.merchant.productcalog;

import java.util.HashMap;
import java.util.Map;

import org.thoughtworks.assessment.merchant.productcalog.types.PriceInCredits;
import org.thoughtworks.assessment.merchant.productcalog.types.ProductName;

public final class ProductCatalog {

    private final Map<ProductName,PriceInCredits>  priceByName;
    
    public ProductCatalog() {

        this.priceByName = new HashMap<ProductName,PriceInCredits>();
    }
    
    public void addOrReplaceProduct(final ProductName product, final PriceInCredits price){
        priceByName.put(product, price);
    }
    
    public PriceInCredits getPrice(final ProductName product){
        return priceByName.get(product);
    }
}
