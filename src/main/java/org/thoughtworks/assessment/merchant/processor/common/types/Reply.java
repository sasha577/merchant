package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * Represents the reply from the merchant. 
 */
@SuppressWarnings("serial")
public final class Reply extends SingleBasedValue<String>{

    /**
     * Constructor
     */
    public Reply(final String value) {
        super(value);
    }

}
