package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * Represents the request to the merchant.
 */
@SuppressWarnings("serial")
public final class Request extends SingleBasedValue<String>{

    /**
     * Constructor
     */
    public Request(final String value) {
        super(value);
    }

}
