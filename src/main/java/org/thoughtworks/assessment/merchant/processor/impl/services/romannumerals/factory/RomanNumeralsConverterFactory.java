package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.factory;

import java.util.List;

import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.converter.RomanNumeralsConverterImpl;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.StateGraphComputer;

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
