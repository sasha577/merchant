package org.thoughtworks.assessment.merchant.romannumerals.impl.converter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.exceptions.WrongRomanNumberException;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;

public final class RomanNumeralsConverterImpl implements RomanNumeralsConverter{
    
    private final List<State> stateGraph;
    
    public RomanNumeralsConverterImpl(final List<State> stateGraph) {
        this.stateGraph = stateGraph;
    }

    @Override
    public ArabicNumber toArabicNumber(final RomanNumber number) throws WrongRomanNumberException{
        
        checkNotEmpty(number);
        
        Collection<State> possibleStates = stateGraph;
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
    
    private static void checkNotEmpty(final RomanNumber number) throws WrongRomanNumberException{
        if(number.getValue().isEmpty()){
            throw new WrongRomanNumberException(number);
        }
    }
}
