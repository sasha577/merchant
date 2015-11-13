package org.thoughtworks.assessment.merchant.processor.impl.base;

import java.util.Optional;

import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;

/**
 * <p>RequestReviser interface.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public interface RequestReviser {

    /**
     * <p>process.</p>
     *
     * @param request a {@link org.thoughtworks.assessment.merchant.processor.common.types.Request} object.
     * @return a {@link java.util.Optional} object.
     */
    public Optional<Replay> process(final Request request);
    /**
     * <p>isResposibleFor.</p>
     *
     * @param request a {@link org.thoughtworks.assessment.merchant.processor.common.types.Request} object.
     * @return a boolean.
     */
    public boolean isResposibleFor(final Request request);
}
