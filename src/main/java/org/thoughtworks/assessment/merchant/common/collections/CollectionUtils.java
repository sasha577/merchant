package org.thoughtworks.assessment.merchant.common.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * 
 * The collection of the methods that simplify using of collection manipulation.
 *
 */
public final class CollectionUtils {

    /**
     * Returns a new list consisting of the results of applying the given
     * function to the elements of this collection.
     */
    public static <K,M> List<M> map(final Collection<K> list, final Function<? super K, ? extends M> mapper){
        
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    /**
     * Returns a list consisting of the elements of given list that match
     * the given predicate.
     */
    public static <K> List<K> filter(final Collection<K> list, final Predicate<? super K> predicate){
        
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    /**
     * Returns a map whose keys and values are the result of applying the provided
     * mapping functions to the input elements.
     */
    public static <T,K,V> Map<K,V> toMap( 
            final Collection<T> col, 
            final Function<? super T, ? extends K> keyMapper, 
            final Function<? super T, ? extends V> valueMapper){
        
        return col.stream().collect(Collectors.toMap(keyMapper,valueMapper));
    }
    
    /**
     * Creates a new collection by the adding of a new element to the end of the given one.
     */
    public static <T> List<T> add(final Collection<? extends T> collection, final T element){
        
        final List<T> result = new ArrayList<T>(collection.size()+1);
        
        result.addAll(collection);
        result.add(element);
        
        return result;
    }

    private CollectionUtils() {
    	// NONE
    }

}
