package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class Request extends SingleValue<String>{

    public Request(final String value) {
        super(value);
    }

}
