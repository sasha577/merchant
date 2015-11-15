package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * Represents the Arabic numeral.
 * For example: 22, 103
 */
@SuppressWarnings("serial")
public final class ArabicNumber extends SingleBasedValue<Integer>{

    /**
     * Factory method.
     */
    public static ArabicNumber valueOf( final int i){
        return new ArabicNumber(i);
    }
    
    /**
     * Constructor.
     */
    public ArabicNumber(final int value) {
        super(value);
    }

}
