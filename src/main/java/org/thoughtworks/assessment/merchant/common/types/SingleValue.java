package org.thoughtworks.assessment.merchant.common.types;

import java.io.Serializable;

import org.thoughtworks.assessment.merchant.common.types.utils.ObjectUtils;

/**
 * serves as base class for all business classes with a single member.
 * Takes care of equals, hashValue and toString methods.
 * 
 * @param <T>
 */
@SuppressWarnings("serial")
public class SingleValue<T> implements Serializable{
    
    private final T value;

    public SingleValue(final T value) {
        assert value != null;
        
        this.value = value;
    }

    public T getValue() {
        return value;
    }
    
    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(final Object obj) {
        if (obj == null) { return false; }
        if (obj == this) { return true; }
        if (obj.getClass() != getClass()) {return false;}
        final SingleValue<T> rhs = (SingleValue<T>) obj;

        return value.equals(rhs.value);
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString(){
        return String.format("%s[%s]",this.getClass().getSimpleName(), ObjectUtils.toString(value));

    }
}
