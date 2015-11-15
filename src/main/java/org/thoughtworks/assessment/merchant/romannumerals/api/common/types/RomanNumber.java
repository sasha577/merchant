package org.thoughtworks.assessment.merchant.romannumerals.api.common.types;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanLiteral;

/**
 * Represents the Roman numeral.
 * 
 * For example: IV, MXXXI
 */
@SuppressWarnings("serial")
public final class RomanNumber extends SingleBasedValue<Collection<RomanNumberLiteral>>{

    /**
     * Creates an class instance from the string.
     * Each character of the string is considered as a Roman literal: I,V ....
     * 
     * @throws WrongRomanLiteral if one of the literals could not be found.
     */
    public static RomanNumber valueOf(final String romanNumber) throws WrongRomanLiteral{

        final List<RomanNumberLiteral> string = 
                romanNumber.chars().mapToObj(c -> RomanNumberLiteral.of((char)c)).collect(Collectors.toList());
        
        return new RomanNumber( string );
    }
    
    /**
     * Constructor.
     */
    public RomanNumber(final Collection<RomanNumberLiteral> value) {
        super(value);
    }

    /**
     * Gets the user friendly representation of this Roman number. 
     */
    public String toLiteral(){
        return getValue().stream().map(p -> p.name()).collect(Collectors.joining(""));
    }
}
