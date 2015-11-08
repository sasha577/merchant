package org.thoughtworks.assessment.merchant.romannumerals.api.exceptions;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;

@SuppressWarnings("serial")
public final class WrongRomanNumberException extends Exception{
    
    public WrongRomanNumberException(final RomanNumber number) {
        super(String.format("wrong roman number: %s", number.toLiteral()));
    }
}