package org.thoughtworks.assessment.merchant.common.types.base;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.base.utils.ObjectUtils;


/**
 *
 * Simplifies the definition of business classes consisting of two members.
 * 
 * To define a new immutable Value-Object-Class you have only to inherit from this one.
 * 
 * The getter are defined as protected to enforce the user to implement the meaningful one
 * .
 * The standard Object-operation are defined as final in order to prevent overriding and potential contract break.
 *
 */
@SuppressWarnings("serial")
public abstract class PairBasedValue<T1,T2> implements Serializable{

    private final T1 first;
    private final T2 second;

    /**
     * <p>Constructor for AbstractPair.</p>
     *
     * @param first a T1 object.
     * @param second a T2 object.
     */
    protected PairBasedValue(final T1 first, final T2 second) {
        assert first != null && second != null;
        
        this.first=first;
        this.second=second;
    }

    /**
     * Get first part.
     * The method is protected in order to enforce the user to implement meaningful getter.
     */
    protected T1 getFirst() {
        return first;
    }

    /**
     * Get second part.
     * The method is protected in order to enforce the user to implement meaningful getter.
     */
    protected T2 getSecond() {
        return second;
    }

    /** {@inheritDoc} */
    @Override
    public final String toString(){
        return String.format("%s[%s,%s]",this.getClass().getSimpleName(), ObjectUtils.toString(first),ObjectUtils.toString(second));
    }

    /** {@inheritDoc} */
    @Override
    public final int hashCode() {
        return ObjectUtils.hashCode(first)*13+ObjectUtils.hashCode(second)*7;
    }

    /** {@inheritDoc} */
    @Override
    public final boolean equals(final Object obj) {
        
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        
        @SuppressWarnings("rawtypes")
        final PairBasedValue rhs = (PairBasedValue) obj;
        
        return ObjectUtils.areEqual(this.getFirst(), rhs.getFirst()) && ObjectUtils.areEqual(this.getSecond(), rhs.getSecond()); 
    }
}
