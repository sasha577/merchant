package org.thoughtworks.assessment.merchant.processor.impl.services.productcatalog.api.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * The name of the particular product
 */
@SuppressWarnings("serial")
public class ProductName  extends SingleBasedValue<String>{

    /**
     * Constructor.
     */
    public ProductName(final String value) {
        super(value);
    }

    /**
     * Indicates that the product is not known.
     */
    public static final class NotDefinedProductException extends Exception{
        
        /**
         * Constructor.
         *
         * @param productName the product name caused the exception. 
         */
        public NotDefinedProductException(final ProductName productName){
            super(String.format("product %s is not defined", productName.getValue()));
        }

    }

}
