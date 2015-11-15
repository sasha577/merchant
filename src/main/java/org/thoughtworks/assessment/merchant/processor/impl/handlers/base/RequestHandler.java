package org.thoughtworks.assessment.merchant.processor.impl.handlers.base;

import java.util.Optional;

import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;

/**
 * Request handler.
 *
 * In order to decomposes the algorithm
 * there is a dedicated handler type for each type of request.
 *   
 */
public interface RequestHandler {

    /**
     * Provides a reply to the request.  
     */
    public Optional<Reply> process(final Request request);

    /**
     * Checks whether this handler can handle the given request.
     */
    public boolean isResposibleFor(final Request request);
}
