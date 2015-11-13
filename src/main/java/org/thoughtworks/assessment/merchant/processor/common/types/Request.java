package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>Request class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class Request extends SingleBasedValue<String>{

    /**
     * <p>Constructor for Request.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    public Request(final String value) {
        super(value);
    }

}
