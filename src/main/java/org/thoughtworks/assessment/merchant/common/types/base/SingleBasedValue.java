package org.thoughtworks.assessment.merchant.common.types.base;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.base.utils.ObjectUtils;

/**
*
* Simplifies the definition of business classes consisting of one member.
* 
* To define a new immutable Value-Object-Class you have only to inherit from this one.
* 
* The standard Object-operation are defined as final in order to prevent overriding and so prevents the contract break.
*
*/
@SuppressWarnings("serial")
public class SingleBasedValue<T> implements Serializable{
    
    private final T value;

    /**
     * Constructor.
     */
    public SingleBasedValue(final T value) {
        this.value = value;
    }

    /**
     * Return the value.
     */
    public final T getValue() {
        return value;
    }
    
    /** {@inheritDoc} */
    @SuppressWarnings("unchecked")
    @Override
    public final boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        final SingleBasedValue<T> rhs = (SingleBasedValue<T>) obj;

        return ObjectUtils.areEqual(value,rhs.value);
    }
    
    /** {@inheritDoc} */
    @Override
    public final int hashCode() {
        return ObjectUtils.hashCode(value);
    }

    /** {@inheritDoc} */
    @Override
    public final String toString(){
        return String.format("%s[%s]",this.getClass().getSimpleName(), ObjectUtils.toString(value));

    }
}
