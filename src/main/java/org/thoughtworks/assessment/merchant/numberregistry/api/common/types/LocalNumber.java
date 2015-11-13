package org.thoughtworks.assessment.merchant.numberregistry.api.common.types;

import java.util.Collection;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;

/**
 * <p>LocalNumber class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class LocalNumber extends SingleBasedValue<Collection<LocalNumberLiteral>>{

    /**
     * <p>Constructor for LocalNumber.</p>
     *
     * @param literals a {@link java.util.Collection} object.
     */
    public LocalNumber(final Collection<LocalNumberLiteral> literals) {
        super(literals);
    }

    /**
     * <p>toLiteral.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toLiteral(){
        return getValue().stream().map(p -> p.getValue()).collect(Collectors.joining(" "));
    }

}
