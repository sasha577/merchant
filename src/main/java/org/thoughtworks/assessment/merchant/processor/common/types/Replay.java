package org.thoughtworks.assessment.merchant.processor.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class Replay extends SingleValue<String>{

    public static Replay NONE = new Replay("");
    
    public Replay(final String value) {
        super(value);
    }

}
