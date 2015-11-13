package org.thoughtworks.assessment.merchant.productcatalog.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * <p>PriceInCredits class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class PriceInCredits extends SingleBasedValue<Fraction>{

    /**
     * <p>Constructor for PriceInCredits.</p>
     *
     * @param value a {@link org.thoughtworks.assessment.merchant.common.types.Fraction} object.
     */
    public PriceInCredits(final Fraction value) {
        super(value);
    }

}
