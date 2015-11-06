package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberSymbol;

public final class Evaluator {
    
    @SuppressWarnings("serial")
    public final class WrongNumberException extends Exception{
        
        public WrongNumberException(final RomanNumber number) {
            super(String.format("wrong roman number: %s", number.toLiteral()));
        }
    }
    
    private final List<State> states;
    
    public Evaluator(final List<State> states) {
        this.states = states;
    }

    public ArabicNumber evaluate(final RomanNumber number) throws WrongNumberException{
        
        Collection<State> possibleStates = states;
        int result = 0;
        
        for(final RomanNumberSymbol s: number.getValue()){
            
            final Optional<State> nextState = 
                    possibleStates.stream().filter(state -> state.getSymbol() == s).findFirst();
            
            if(!nextState.isPresent()){
                throw new WrongNumberException(number);
            }
            
            result += nextState.get().getValue();
            
            possibleStates = nextState.get().getSuccessors();
        }
        
        return new ArabicNumber(result);
    }
}
