package org.thoughtworks.assessment.merchant.productcatalog.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public class ProductName  extends SingleValue<String>{

    public ProductName(final String value) {
        super(value);
    }

}
