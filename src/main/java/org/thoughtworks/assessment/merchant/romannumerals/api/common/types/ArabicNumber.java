package org.thoughtworks.assessment.merchant.romannumerals.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class ArabicNumber extends SingleValue<Integer>{

    public static ArabicNumber valueOf( final int i){
        return new ArabicNumber(i);
    }
    
    public ArabicNumber(final Integer value) {
        super(value);
    }

}
