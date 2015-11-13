package org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>LocalNumberLiteral class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class LocalNumberLiteral extends SingleBasedValue<String>{

    /**
     * <p>of.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @return a {@link org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral} object.
     */
    public static LocalNumberLiteral of(final String value){
        return new LocalNumberLiteral(value);
    }
    
    private LocalNumberLiteral(final String value) {
        super(value);
    }

}
