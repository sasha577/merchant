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
public enum RomanNumberSymbol{
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);
    
    private final int value;
    
    private RomanNumberSymbol(final int value) {
        this.value = value;
    }
    
    public int getValue(){
        return value;
    }
    
    public boolean isHigherThen( final RomanNumberSymbol other){
        return this.value > other.value;
    }

    public boolean isHigherOrEqualThen( final RomanNumberSymbol other){
        return this.value >= other.value;
    }

    /**
     * gets all literals that have lower value than this one.
     * @return all literals that have lower value than this one.
     */
    public Collection<RomanNumberSymbol> getLowerValues(){

        final List<RomanNumberSymbol> allValues = Arrays.asList(RomanNumberSymbol.values());

        return CollectionUtils.filter(allValues, (p -> p.value < this.value) );
    }
    
    /**
     * search enum item by the char.
     * @param c char to search
     * @return enum item
     * @throws NoSuchElementException if not found
     */
    public static RomanNumberSymbol getBy(final char c){
        
        final List<RomanNumberSymbol> allValues = Arrays.asList(RomanNumberSymbol.values());
        
        final Optional<RomanNumberSymbol> result = allValues.stream().filter(p -> p.name().charAt(0)==c).findFirst();
        
        return result.get();
    }
    
    public static final Comparator<RomanNumberSymbol> VALUE_COMPARATOR = new Comparator<RomanNumberSymbol>() {
        
        @Override
        public int compare(final RomanNumberSymbol o1, final RomanNumberSymbol o2) {
            assert o1 != null;
            assert o2 != null;

            return o1.value - o2.value;
        }
    };
}
