package org.thoughtworks.assessment.merchant.common.types;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.base.PairBasedValue;


/**
 *
 * a pair of objects
 *
 * @param <T1> type of first object
 * @param <T2> type of second object
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class Pair<T1,T2> extends PairBasedValue<T1, T2> implements Serializable{

    /**
     * <p>getFirstValue.</p>
     *
     * @return a T1 object.
     */
    public T1 getFirstValue() {
        return super.getFirst();
    }

    /**
     * <p>getSecondValue.</p>
     *
     * @return a T2 object.
     */
    public T2 getSecondValue() {
        return super.getSecond();
    }

    /**
     * create a pair of two object
     *
     * @param <T1> type of first object
     * @param <T2> type of second object
     * @param first first object
     * @param second second object
     * @return a pair of two object
     */
    public static <T1,T2> Pair<T1,T2> of(final T1 first, final T2 second) {
        return new Pair<T1,T2>(first, second);
    }

    private Pair(final T1 first, final T2 second) {
        super(first,second);
    }

    
}
