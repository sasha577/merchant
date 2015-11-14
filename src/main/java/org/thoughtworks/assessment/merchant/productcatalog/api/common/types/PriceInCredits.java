package org.thoughtworks.assessment.merchant.productcatalog.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;

/**
 * Price in Credits of one item of certain product.
 * Because the price can be prompted as a sum for several items,
 * fraction is used to keep the value.  
 */
@SuppressWarnings("serial")
public final class PriceInCredits extends SingleBasedValue<Fraction>{

    /**
     * Constructor.
     */
    public PriceInCredits(final Fraction value) {
        super(value);
    }

}
