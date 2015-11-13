package org.thoughtworks.assessment.merchant.numberregistry.api.common.types;

import java.util.Collection;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;

/**
 * Represents a local number as a list of local literals.
 * For example 'pish tegj glob glob' from the request 'how much is pish tegj glob glob ?'.
 */
@SuppressWarnings("serial")
public final class LocalNumber extends SingleBasedValue<Collection<LocalNumberLiteral>>{

    /**
     * Constructor.
     */
    public LocalNumber(final Collection<LocalNumberLiteral> literals) {
        super(literals);
    }

    /**
     * Returns the user friendly representation of this object. 
     * For example 'pish tegj glob glob'.
     */
    public String toLiteral(){
        return getValue().stream().map(p -> p.getValue()).collect(Collectors.joining(" "));
    }

}
