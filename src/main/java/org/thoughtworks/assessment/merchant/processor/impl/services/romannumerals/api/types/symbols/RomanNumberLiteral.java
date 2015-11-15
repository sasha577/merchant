package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;

/**
 * Symbols used in Roman numerals.
 */
public enum RomanNumberLiteral{
    I(1),
    V(5),
    X(10),
    L(50),
    C(100),
    D(500),
    M(1000);
    
    /**
     * the value of this literal.
     */
    private final int value;
    
    private RomanNumberLiteral(final int value) {
        this.value = value;
    }
    
    /**
     * Gets the value of this literal
     */
    public int getValue(){
        return value;
    }
    
    /**
     * Says whether the value of this literal is higher than of one other. 
     */
    public boolean isHigherThen( final RomanNumberLiteral other){
        return this.value > other.value;
    }

    /**
     * Says whether the value of this literal is higher or equal than of one other. 
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
     * @throws WrongRomanLiteral if no item could be found
     */
    public static RomanNumberLiteral of(final char c) throws WrongRomanLiteral{
        
        final List<RomanNumberLiteral> allValues = Arrays.asList(RomanNumberLiteral.values());
        
        final Optional<RomanNumberLiteral> result = allValues.stream().filter(p -> p.name().charAt(0)==c).findFirst();
        
        if(!result.isPresent()){
            throw new WrongRomanLiteral(c);
        }
        return result.get();
    }
    
    /**
     * 
     * Indicates that there is a char that does not exists in Roman numeral literals.
     * For example 'A'. 
     */
    @SuppressWarnings("serial")
    public static final class WrongRomanLiteral extends RuntimeException{
        
        private final char wrongLiteral;
        /**
         * Constructor.
         *
         * @param wrongLiteral a is a char that does not exists in Roman numeral literals. 
         */
        public WrongRomanLiteral(final char wrongLiteral) {
            super(String.format("wrong roman literal: %s", wrongLiteral));
            this.wrongLiteral = wrongLiteral;
        }
        
        public char getWrongLiteral(){
            return wrongLiteral;
        }
    }
    
}
