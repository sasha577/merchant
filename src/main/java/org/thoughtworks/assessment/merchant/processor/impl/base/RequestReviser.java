package org.thoughtworks.assessment.merchant.processor.impl.base;

import org.thoughtworks.assessment.merchant.processor.common.types.Replay;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;

public interface RequestReviser {

    public Replay process(final Request request);
    public boolean isResposibleFor(final Request request);
}
