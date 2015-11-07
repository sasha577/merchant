package org.thoughtworks.assessment.merchant.common.types.base.utils;

public final class ObjectUtils {

    public static <T> boolean areEqual(final T o1, final T o2){
        
        if( o1 == o2 ){
            return true;
        }
        else if( o1 != null && o2 != null){
            return o1.equals(o2);
        }
        else{
            return false;
        }
    }
    
    public static String toString( final Object o){
        
        return o != null ? o.toString(): "null";
    }

    public static int hashCode( final Object o){ 

        return o != null ? o.hashCode(): 1;
    }
    
    public static <T extends Comparable<T>> int compare(final T o1, final T o2){
        
        if( o1 == o2 ){
            return 0;
        }
        else if( o1 != null && o2 != null){
            return o1.compareTo(o2);
        }
        else{
            return o1 != null ? 1 : -1; // null first
        }
    }
    
    private ObjectUtils() {
    }
}
