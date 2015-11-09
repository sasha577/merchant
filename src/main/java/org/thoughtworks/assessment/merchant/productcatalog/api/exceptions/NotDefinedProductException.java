package org.thoughtworks.assessment.merchant.productcatalog.api.exceptions;

import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;

@SuppressWarnings("serial")
public final class NotDefinedProductException extends Exception{
    
    public NotDefinedProductException(final ProductName productName){
        super(String.format("product %s is not defined", productName.getValue()));
    }

}
