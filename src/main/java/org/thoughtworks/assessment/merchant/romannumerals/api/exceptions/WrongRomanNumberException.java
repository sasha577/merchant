package org.thoughtworks.assessment.merchant.romannumerals.api.exceptions;

import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;

/**
 * <p>WrongRomanNumberException class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class WrongRomanNumberException extends Exception{
    
    /**
     * <p>Constructor for WrongRomanNumberException.</p>
     *
     * @param number a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber} object.
     */
    public WrongRomanNumberException(final RomanNumber number) {
        super(String.format("wrong roman number: %s", number.toLiteral()));
    }
}
