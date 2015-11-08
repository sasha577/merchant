package org.thoughtworks.assessment.merchant.romannumerals.impl;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.State;

public final class RomanNumeralsConverterImpl implements RomanNumeralsConverter{
    
    private final List<State> rootStates;
    
    public RomanNumeralsConverterImpl(final List<State> states) {
        this.rootStates = states;
    }

    @Override
    public ArabicNumber toArabicNumber(final RomanNumber number) throws WrongRomanNumberException{
        
        Collection<State> possibleStates = rootStates;
        int result = 0;
        
        for(final RomanNumberLiteral s: number.getValue()){
            
            final Optional<State> nextState = 
                    possibleStates.stream().filter(state -> state.getLiteral() == s).findFirst();
            
            if(!nextState.isPresent()){
                throw new WrongRomanNumberException(number);
            }
            
            result += nextState.get().getValue();
            
            possibleStates = nextState.get().getSuccessors();
        }
        
        return new ArabicNumber(result);
    }
}
