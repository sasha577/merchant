package org.thoughtworks.assessment.merchant.productcatalog.api.exceptions;

import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;

/**
 * Indicates that the product is not known.
 */
@SuppressWarnings("serial")
public final class NotDefinedProductException extends Exception{
    
    /**
     * Constructor.
     *
     * @param productName the product name caused the exception. 
     */
    public NotDefinedProductException(final ProductName productName){
        super(String.format("product %s is not defined", productName.getValue()));
    }

}
