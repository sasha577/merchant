package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.converter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.ArabicNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber.WrongRomanNumberException;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.common.state.State;

/**
 * Converters the Roman numeral into the Arabic numeral.
 */
public final class RomanNumeralsConverterImpl implements RomanNumeralsConverter{
    
    private final List<State> stateGraph;
    
    /**
     * Constructor.
     *
     * @param stateGraph a list of the initial states of the graph.
     */
    public RomanNumeralsConverterImpl(final List<State> stateGraph) {
        this.stateGraph = stateGraph;
    }

    /** {@inheritDoc} */
    @Override
    public ArabicNumber toArabicNumber(final RomanNumber number) throws WrongRomanNumberException{
        
        checkNotEmpty(number);
        
        Collection<State> possibleStates = stateGraph;
        int result = 0;
        
        for(final RomanNumberLiteral literal: number.getValue()){
            
            // Search for the next state matching literal.
            final Optional<State> matchigState = 
                    possibleStates.stream().filter(state -> state.getLiteral() == literal).findFirst();
            
            if(!matchigState.isPresent()){
                // No matching state was found, so Roman literal is not valid. 
                throw new WrongRomanNumberException(number);
            }
            
            // Accumulate the value of the state.
            // Subtractions are already considered in the state value.  
            result += matchigState.get().getValue();
            
            // Get next possible states.
            possibleStates = matchigState.get().getSuccessors();
        }
        
        return new ArabicNumber(result);
    }
    
    /**
     * Checks whether the numeral contains any literal.  
     */
    private static void checkNotEmpty(final RomanNumber number) throws WrongRomanNumberException{
        if(number.getValue().isEmpty()){
            throw new WrongRomanNumberException(number);
        }
    }
}
