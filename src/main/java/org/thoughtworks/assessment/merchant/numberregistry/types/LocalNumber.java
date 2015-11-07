package org.thoughtworks.assessment.merchant.numberregistry.types;

import java.util.Collection;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;

@SuppressWarnings("serial")
public final class LocalNumber extends SingleValue<Collection<LocalNumberLiteral>>{

    public LocalNumber(final Collection<LocalNumberLiteral> literals) {
        super(literals);
    }

}
