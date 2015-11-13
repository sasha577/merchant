package org.thoughtworks.assessment.merchant.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.PairBasedValue;


/**
 * The class represents 'fraction' math abstraction
 * and offers the arithmetical operations above it.
 *
 */
@SuppressWarnings("serial")
public final class Fraction extends PairBasedValue<Integer, Integer>{

    /**
     * The factory method.
     */
    public static Fraction of(final int numerator, final int denominator){
        
        // TODO normalize fraction in order to reduce of common denominator.
        return new Fraction(numerator, denominator);
    }

    /**
     * The factory method for the integer values.
     * The denominator is set to one.
     */
    public static Fraction of(final int numerator){
        return new Fraction(numerator, 1);
    }
    
    /**
     * Constructor
     */
    private Fraction(final int numerator, final int denominator) {
        super(numerator,denominator);
        assert (denominator != 0): "denominator is zero" ;
    }

    /**
     * Converts the this fraction to an integer by dividing the numerator by the denominator.
     * The rest of the division will be thrown away.
     */
    public int toInteger(){
        return getNumerator()/getDenominator();
    }
    
    /**
     * Creates the new Fraction-object by adding the given to the this one. 
     */
    public Fraction add(final Fraction other){
    	
        final int newNumerator = 
                other.getNumerator()*getDenominator() + getNumerator()*other.getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    /**
     * Creates the new Fraction-object by subtracting the given from the this one. 
     */
    public Fraction subtract(final Fraction other){
    	
        final int newNumerator = 
                getNumerator()*other.getDenominator() - other.getNumerator()*getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    /**
     * Creates the new Fraction-object by multiply the given from with this one. 
     */
    public Fraction multiply(final Fraction other){
    	
        final int newNumerator = 
                getNumerator()*other.getNumerator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    /**
     * Creates the new Fraction-object by subtracting this by the given one. 
     */
    public Fraction divide(final Fraction other){
        final int newNumerator = 
                getNumerator()*other.getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getNumerator();
        
        return of(newNumerator, newDenominator);
    }

    /**
     * Gets the numerator of the fraction.
     */
    private int getNumerator() {
        return super.getFirst();
    }

    /**
     * Gets the denominator of the fraction.
     */
    private int getDenominator() {
        return super.getSecond();
    }
}
