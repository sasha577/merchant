package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>Replay class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class Replay extends SingleBasedValue<String>{

    /** Constant <code>NONE</code> */
    public static Replay NONE = new Replay("");
    
    /**
     * <p>Constructor for Replay.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public Replay(final String value) {
        super(value);
    }

}
