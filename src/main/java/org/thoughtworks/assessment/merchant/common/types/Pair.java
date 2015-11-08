package org.thoughtworks.assessment.merchant.common.types;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.base.AbstractPair;


/**
 * 
 * a pair of objects
 * 
 *
 * @param <T1> type of first object
 * @param <T2> type of second object
 */
@SuppressWarnings("serial")
public final class Pair<T1,T2> extends AbstractPair<T1, T2> implements Serializable{

    public T1 getFirstValue() {
        return super.getFirst();
    }

    public T2 getSecondValue() {
        return super.getSecond();
    }

    /**
     * create a pair of two object
     * @param <T1> type of first object
     * @param <T2> type of second object
     * @param first first object
     * @param second second object
     * @return a pair of two object
     */
    public static <T1,T2> Pair<T1,T2> make(final T1 first, final T2 second) {
        return new Pair<T1,T2>(first, second);
    }

    private Pair(final T1 first, final T2 second) {
        super(first,second);
    }

    
}
