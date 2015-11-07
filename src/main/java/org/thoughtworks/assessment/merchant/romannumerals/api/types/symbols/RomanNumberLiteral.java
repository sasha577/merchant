package org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;

/**
 * 
 * Symbols used in Roman numbers.
 *
 */
public enum RomanNumberLiteral{
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);
    
    private final int value;
    
    private RomanNumberLiteral(final int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    public boolean isHigherThen( final RomanNumberLiteral other){
        return this.value > other.value;
    }

    public boolean isHigherOrEqualThen( final RomanNumberLiteral other){
        return this.value >= other.value;
    }

    /**
     * gets all literals that have lower value than this one.
     * @return all literals that have lower value than this one.
     */
    public Collection<RomanNumberLiteral> getLowerValues(){

        final List<RomanNumberLiteral> allValues = Arrays.asList(RomanNumberLiteral.values());

        return CollectionUtils.filter(allValues, (p -> p.value < this.value) );
    }
    
    /**
     * search enum item by the char.
     * @param c char to search
     * @return enum item
     * @throws NoSuchElementException if not found
     */
    public static RomanNumberLiteral getBy(final char c){
        
        final List<RomanNumberLiteral> allValues = Arrays.asList(RomanNumberLiteral.values());
        
        final Optional<RomanNumberLiteral> result = allValues.stream().filter(p -> p.name().charAt(0)==c).findFirst();
        
        return result.get();
    }
    
    public static final Comparator<RomanNumberLiteral> VALUE_COMPARATOR = new Comparator<RomanNumberLiteral>() {
        
        @Override
        public int compare(final RomanNumberLiteral o1, final RomanNumberLiteral o2) {
            assert o1 != null;
            assert o2 != null;

            return o1.value - o2.value;
        }
    };
}
