package org.thoughtworks.assessment.merchant.common.types;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.utils.ObjectUtils;


/**
 *
 * A pair of two objects.
 * 
 * Serves as base class for all business classes with a two members.
 *
 * @param <T1> type of the first object
 * @param <T2> type of the second object
 */
@SuppressWarnings("serial")
public abstract class AbstractPair<T1,T2> implements Serializable{

    private final T1 first;
    private final T2 second;

    protected AbstractPair(final T1 first, final T2 second) {
    	assert first != null && second != null;
    	
        this.first=first;
        this.second=second;
    }

    /**
     * get first object
     * @return first object
     */
    protected T1 getFirst() {
        return first;
    }

    /**
     * get second object
     * @return second object
     */
    protected T2 getSecond() {
        return second;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString(){
        return String.format("%s[%s,%s]",this.getClass().getSimpleName(), ObjectUtils.toString(first),ObjectUtils.toString(second));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return ObjectUtils.hashCode(first)*13+ObjectUtils.hashCode(second)*7;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        
        @SuppressWarnings("rawtypes")
        final AbstractPair rhs = (AbstractPair) obj;
        return ObjectUtils.areEqual(this.getFirst(), rhs.getFirst()) && ObjectUtils.areEqual(this.getSecond(), rhs.getSecond()); 
    }
}
