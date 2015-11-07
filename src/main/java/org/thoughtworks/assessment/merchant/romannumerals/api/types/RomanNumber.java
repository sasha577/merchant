package org.thoughtworks.assessment.merchant.romannumerals.api.types;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.base.SingleValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

@SuppressWarnings("serial")
public final class RomanNumber extends SingleValue<Collection<RomanNumberLiteral>>{

    public static RomanNumber parse(final String romanNumber){

        final List<RomanNumberLiteral> string = 
                romanNumber.chars().mapToObj(c -> RomanNumberLiteral.getBy((char)c)).collect(Collectors.toList());
        
        return new RomanNumber( string );
    }
    
    public RomanNumber(final Collection<RomanNumberLiteral> value) {
        super(value);
    }

    public String toLiteral(){
        return getValue().stream().map(p -> p.name()).collect(Collectors.joining(""));
    }
}
