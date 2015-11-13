package org.thoughtworks.assessment.merchant.numberregistry.api.exceptions;

import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;

/**
 * <p>UnknownLiteral class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class UnknownLiteral extends Exception{
    
    /**
     * <p>Constructor for UnknownLiteral.</p>
     *
     * @param literal a {@link org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral} object.
     */
    public UnknownLiteral(final LocalNumberLiteral literal){
        super(String.format("unknown literal: %s", literal.getValue()));
    }

}
