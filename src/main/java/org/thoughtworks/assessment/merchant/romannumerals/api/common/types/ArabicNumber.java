package org.thoughtworks.assessment.merchant.romannumerals.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>ArabicNumber class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class ArabicNumber extends SingleBasedValue<Integer>{

    /**
     * <p>valueOf.</p>
     *
     * @param i a int.
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber} object.
     */
    public static ArabicNumber valueOf( final int i){
        return new ArabicNumber(i);
    }
    
    /**
     * <p>Constructor for ArabicNumber.</p>
     *
     * @param value a {@link java.lang.Integer} object.
     */
    public ArabicNumber(final Integer value) {
        super(value);
    }

}
