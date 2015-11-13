package org.thoughtworks.assessment.merchant.romannumerals.api.common.types;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleBasedValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * <p>RomanNumber class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class RomanNumber extends SingleBasedValue<Collection<RomanNumberLiteral>>{

    /**
     * <p>valueOf.</p>
     *
     * @param romanNumber a {@link java.lang.String} object.
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber} object.
     */
    public static RomanNumber valueOf(final String romanNumber){

        final List<RomanNumberLiteral> string = 
                romanNumber.chars().mapToObj(c -> RomanNumberLiteral.of((char)c)).collect(Collectors.toList());
        
        return new RomanNumber( string );
    }
    
    /**
     * <p>Constructor for RomanNumber.</p>
     *
     * @param value a {@link java.util.Collection} object.
     */
    public RomanNumber(final Collection<RomanNumberLiteral> value) {
        super(value);
    }

    /**
     * <p>toLiteral.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toLiteral(){
        return getValue().stream().map(p -> p.name()).collect(Collectors.joining(""));
    }
}
