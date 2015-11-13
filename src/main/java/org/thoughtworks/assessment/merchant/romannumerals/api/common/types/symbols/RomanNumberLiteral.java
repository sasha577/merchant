package org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanLiteral;

/**
 *
 * Symbols used in Roman numbers.
 *
 * @author arubinov
 * @version $Id: $Id
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
    
    /**
     * <p>Getter for the field <code>value</code>.</p>
     *
     * @return a int.
     */
    public int getValue(){
        return value;
    }
    
    /**
     * <p>isHigherThen.</p>
     *
     * @param other a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     * @return a boolean.
     */
    public boolean isHigherThen( final RomanNumberLiteral other){
        return this.value > other.value;
    }

    /**
     * <p>isHigherOrEqualThen.</p>
     *
     * @param other a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     * @return a boolean.
     */
    public boolean isHigherOrEqualThen( final RomanNumberLiteral other){
        return this.value >= other.value;
    }

    /**
     * gets all literals that have lower value than this one.
     *
     * @return all literals that have lower value than this one.
     */
    public Collection<RomanNumberLiteral> getLowerValues(){

        final List<RomanNumberLiteral> allValues = Arrays.asList(RomanNumberLiteral.values());

        return CollectionUtils.filter(allValues, (p -> p.value < this.value) );
    }
    
    /**
     * search enum item by the char.
     *
     * @param c char to search
     * @return enum item
     * @throws WrongRomanLiteral if not found
     */
    public static RomanNumberLiteral of(final char c){
        
        final List<RomanNumberLiteral> allValues = Arrays.asList(RomanNumberLiteral.values());
        
        final Optional<RomanNumberLiteral> result = allValues.stream().filter(p -> p.name().charAt(0)==c).findFirst();
        
        if(!result.isPresent()){
            throw new WrongRomanLiteral(c);
        }
        return result.get();
    }
    
}
