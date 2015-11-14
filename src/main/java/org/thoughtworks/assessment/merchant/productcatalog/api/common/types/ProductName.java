package org.thoughtworks.assessment.merchant.productcatalog.api.common.types;

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

}
