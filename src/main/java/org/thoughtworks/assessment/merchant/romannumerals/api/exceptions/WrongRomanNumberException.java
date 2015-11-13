package org.thoughtworks.assessment.merchant.romannumerals.api.exceptions;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;

/**
 * 
 * Indicates that there is a  not valid sequence a Roman numeral literals.
 * For example 'VV'. 
 */
@SuppressWarnings("serial")
public final class WrongRomanNumberException extends Exception{
    
    /**
     * Constructor.
     *
     * @param number a number that does not match the rules of Roman numerals. 
     */
    public WrongRomanNumberException(final RomanNumber number) {
        super(String.format("wrong roman number: %s", number.toLiteral()));
    }
}
