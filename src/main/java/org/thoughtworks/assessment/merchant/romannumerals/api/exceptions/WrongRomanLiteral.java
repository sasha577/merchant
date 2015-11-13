package org.thoughtworks.assessment.merchant.romannumerals.api.exceptions;

/**
 * 
 * Indicates that there is a char that does not exists in Roman numeral literals.
 * For example 'A'. 
 */
@SuppressWarnings("serial")
public final class WrongRomanLiteral extends RuntimeException{
    
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
