package org.thoughtworks.assessment.merchant.numberregistry.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class LocalNumberLiteral extends SingleValue<String>{

    public LocalNumberLiteral(final String value) {
        super(value);
    }

}
