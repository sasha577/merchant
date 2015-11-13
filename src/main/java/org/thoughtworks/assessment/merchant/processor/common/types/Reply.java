package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>Replay class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class Reply extends SingleBasedValue<String>{

    /** Constant <code>NONE</code> */
    public static Reply NONE = new Reply("");
    
    /**
     * <p>Constructor for Replay.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public Reply(final String value) {
        super(value);
    }

}
