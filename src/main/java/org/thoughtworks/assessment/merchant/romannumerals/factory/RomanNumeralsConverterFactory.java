package org.thoughtworks.assessment.merchant.romannumerals.factory;

import java.util.List;

import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.romannumerals.impl.converter.RomanNumeralsConverterImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.StateGraphComputer;

/**
 * Creates a Roman numerals converter.
 */
public final class RomanNumeralsConverterFactory{

    /**
     * Creates a Roman numerals converter.
     */
    public static RomanNumeralsConverter create(){
        
        final List<State> stateGraph = new StateGraphComputer().create();
        
        return new RomanNumeralsConverterImpl(stateGraph);
        
    }
    
    /**
     * private constructor.
     */
    private RomanNumeralsConverterFactory() {
        // none
    }
}
