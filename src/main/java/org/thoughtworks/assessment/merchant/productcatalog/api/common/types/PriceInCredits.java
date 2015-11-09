package org.thoughtworks.assessment.merchant.productcatalog.api.common.types;

import org.thoughtworks.assessment.merchant.common.types.Fraction;
import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class PriceInCredits extends SingleValue<Fraction>{

    public PriceInCredits(final Fraction value) {
        super(value);
    }

}
