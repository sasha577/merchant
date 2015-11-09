package org.thoughtworks.assessment.merchant.numberregistry.api.common.types;

import java.util.Collection;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;

@SuppressWarnings("serial")
public final class LocalNumber extends SingleValue<Collection<LocalNumberLiteral>>{

    public LocalNumber(final Collection<LocalNumberLiteral> literals) {
        super(literals);
    }

    public String toLiteral(){
        return getValue().stream().map(p -> p.getValue()).collect(Collectors.joining(" "));
    }

}
