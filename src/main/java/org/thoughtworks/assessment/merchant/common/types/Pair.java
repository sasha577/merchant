package org.thoughtworks.assessment.merchant.common.types;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.base.PairBasedValue;


/**
 *
 * A pair of two values.
 * Intended to be used as a tuple.
 * 
 * Should not be used in a public interfaces.  
 *
 */
@SuppressWarnings("serial")
public final class Pair<T1,T2> extends PairBasedValue<T1, T2> implements Serializable{

    /**
     * Gets the first value.
     */
    public T1 getFirstValue() {
        return super.getFirst();
    }

    /**
     * Gets the second value.
     */
    public T2 getSecondValue() {
        return super.getSecond();
    }

    /**
     * The factory method.
     */
    public static <T1,T2> Pair<T1,T2> of(final T1 first, final T2 second) {
        return new Pair<T1,T2>(first, second);
    }

    /**
     * Constructor. 
     */
    private Pair(final T1 first, final T2 second) {
        super(first,second);
    }

    
}
