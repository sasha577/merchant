package org.thoughtworks.assessment.merchant.common.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class CollectionUtils {

    public static <K,M> List<M> map(final Collection<K> list, final Function<? super K, ? extends M> mapper){
        
        return list.stream().map(mapper).collect(Collectors.toList());
    }

    public static <K> List<K> filter(final Collection<K> list, final Predicate<? super K> predicate){
        
        return list.stream().filter(predicate).collect(Collectors.toList());
    }

    public static <T,K,V> Map<K,V> toMap( 
            final Collection<T> col, 
            final Function<? super T, ? extends K> keyMapper, 
            final Function<? super T, ? extends V> valueMapper){
        
        return col.stream().collect(Collectors.toMap(keyMapper,valueMapper));
    }
    
    public static <T> List<T> add(final Collection<? extends T> c1, final T c2){
        
        final List<T> result = new ArrayList<T>(c1.size()+1);
        
        result.addAll(c1);
        result.add(c2);
        
        return result;
    }

    private CollectionUtils() {
    }

}
