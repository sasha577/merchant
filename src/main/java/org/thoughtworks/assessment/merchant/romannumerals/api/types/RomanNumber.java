package org.thoughtworks.assessment.merchant.romannumerals.api.types;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.thoughtworks.assessment.merchant.common.types.SingleValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberSymbol;

@SuppressWarnings("serial")
public final class RomanNumber extends SingleValue<Collection<RomanNumberSymbol>>{

    public static RomanNumber parse(final String romanNumber){

        final List<RomanNumberSymbol> string = 
                romanNumber.chars().mapToObj(c -> RomanNumberSymbol.getBy((char)c)).collect(Collectors.toList());
        
        return new RomanNumber( string );
    }
    
    public RomanNumber(final Collection<RomanNumberSymbol> value) {
        super(value);
    }

    public String toLiteral(){
        return getValue().stream().map(p -> p.name()).collect(Collectors.joining(""));
    }
}
