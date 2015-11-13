package org.thoughtworks.assessment.merchant.productcatalog.api.exceptions;

import org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName;

/**
 * <p>NotDefinedProductException class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class NotDefinedProductException extends Exception{
    
    /**
     * <p>Constructor for NotDefinedProductException.</p>
     *
     * @param productName a {@link org.thoughtworks.assessment.merchant.productcatalog.api.common.types.ProductName} object.
     */
    public NotDefinedProductException(final ProductName productName){
        super(String.format("product %s is not defined", productName.getValue()));
    }

}
