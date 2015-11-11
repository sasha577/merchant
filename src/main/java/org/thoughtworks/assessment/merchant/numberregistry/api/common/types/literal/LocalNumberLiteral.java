package org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class LocalNumberLiteral extends SingleValue<String>{

    public static LocalNumberLiteral of(final String value){
        return new LocalNumberLiteral(value);
    }
    
    private LocalNumberLiteral(final String value) {
        super(value);
    }

}
