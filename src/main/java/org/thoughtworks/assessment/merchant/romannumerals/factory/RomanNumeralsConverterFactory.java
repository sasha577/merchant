package org.thoughtworks.assessment.merchant.romannumerals.factory;

import java.util.List;

import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.romannumerals.impl.converter.RomanNumeralsConverterImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.StateGraphComputer;

public final class RomanNumeralsConverterFactory{

    public static RomanNumeralsConverterImpl create(){
        
        final List<State> stateGraph = new StateGraphComputer().create();
        
        return new RomanNumeralsConverterImpl(stateGraph);
        
    }
    
    private RomanNumeralsConverterFactory() {
        // none
    }
}
