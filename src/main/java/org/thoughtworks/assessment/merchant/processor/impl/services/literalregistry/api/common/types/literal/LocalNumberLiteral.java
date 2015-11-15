package org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * Represents a local number literal.
 * For example a 'grob' from the request 'glob is I'. 
 */
@SuppressWarnings("serial")
public final class LocalNumberLiteral extends SingleBasedValue<String>{

    /**
     * The factory method.
     */
    public static LocalNumberLiteral of(final String value){
        return new LocalNumberLiteral(value);
    }
    
    /**
     * Constructor.
     */
    private LocalNumberLiteral(final String value) {
        super(value);
    }

}
