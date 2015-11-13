package org.thoughtworks.assessment.merchant.common.types.base.utils;

/**
 * the collection of utility methods for implementing 
 * the standard {@link java.lang.Object} operations considering 'null' values.    
 */
public final class ObjectUtils {

    /**
     * <p>areEqual.</p>
     *
     * @param o1 a T object.
     * @param o2 a T object.
     * @param <T> a T object.
     * @return a boolean.
     */
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
    
    /**
     * <p>toString.</p>
     *
     * @param o a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     */
    public static String toString( final Object o){
        
        return o != null ? o.toString(): "null";
    }

    /**
     * <p>hashCode.</p>
     *
     * @param o a {@link java.lang.Object} object.
     * @return a int.
     */
    public static int hashCode( final Object o){ 

        return o != null ? o.hashCode(): 1;
    }
    
    /**
     * <p>compare.</p>
     *
     * @param o1 a T object.
     * @param o2 a T object.
     * @param <T> a T object.
     * @return a int.
     */
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
