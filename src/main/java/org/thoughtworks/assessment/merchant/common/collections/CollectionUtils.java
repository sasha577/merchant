package org.thoughtworks.assessment.merchant.common.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class CollectionUtils {

    public static <T> List<T> concat(final Collection<? extends T> c1, final Collection<? extends T> c2){
        
        final List<T> result = new ArrayList<T>(c1.size()+c2.size());
        
        result.addAll(c1);
        result.addAll(c2);
        
        return result;
    }

    public static <T> List<T> add(final Collection<? extends T> c1, final T c2){
        
        final List<T> result = new ArrayList<T>(c1.size()+1);
        
        result.addAll(c1);
        result.add(c2);
        
        return result;
    }

    public static <K,M> List<M> map(final Collection<K> list, final Function<? super K, ? extends M> mapper){
        
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <K> List<K> filter(final Collection<K> list, final Predicate<? super K> predicate){
        
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T> Optional<T> getLast(final List<T> col){
        
        return col.isEmpty() ? Optional.empty() : Optional.of(col.get(col.size()-1));
    }

    public static <T> List<T> reverse(final List<T> list){
        
        final List<T> result = new ArrayList<T>(list.size());
        
        result.addAll(list);
        
        Collections.reverse(result);
        
        return result;
    }

    public static <T> long occurrence(final T sample, final Collection<T> col) {

        return col.stream().filter( p -> p.equals(sample)).count();
    }
    
    public static <T,K,V> Map<K,V> toMap( 
            final Collection<T> col, 
            final Function<? super T, ? extends K> keyMapper, 
            final Function<? super T, ? extends V> valueMapper){
        
        return col.stream().collect(Collectors.toMap(keyMapper,valueMapper));
    }
    
    private CollectionUtils() {
    }

}
