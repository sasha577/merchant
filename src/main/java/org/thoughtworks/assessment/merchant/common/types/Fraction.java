package org.thoughtworks.assessment.merchant.common.types;

import org.thoughtworks.assessment.merchant.common.types.base.AbstractPair;


@SuppressWarnings("serial")
public final class Fraction extends AbstractPair<Integer, Integer>{

    public static Fraction of(final int numerator, final int denominator){
        
        // TODO normalize fraction in order to reduce of common denominator.
        return new Fraction(numerator, denominator);
    }

    
    private Fraction(final int numerator, final int denominator) {
        super(numerator,denominator);
        assert (denominator != 0): "denominator is zero" ;
    }

    public int toInteger(){
        return getNumerator()/getDenominator();
    }
    
    public Fraction add(final Fraction other)
    {
        final int newNumerator = 
                other.getNumerator()*getDenominator() + getNumerator()*other.getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    public Fraction subtract(final Fraction other)
    {
        final int newNumerator = 
                getNumerator()*other.getDenominator() - other.getNumerator()*getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    public Fraction multiply(final Fraction other)
    {
        final int newNumerator = 
                getNumerator()*other.getNumerator();
        
        final int newDenominator = 
                getDenominator()*other.getDenominator();
        
        return of(newNumerator, newDenominator);
    }

    public Fraction divide(final Fraction other)
    {
        final int newNumerator = 
                getNumerator()*other.getDenominator();
        
        final int newDenominator = 
                getDenominator()*other.getNumerator();
        
        return of(newNumerator, newDenominator);
    }


    private int getNumerator() {
        return super.getFirst();
    }

    private int getDenominator() {
        return super.getSecond();
    }


    
}
