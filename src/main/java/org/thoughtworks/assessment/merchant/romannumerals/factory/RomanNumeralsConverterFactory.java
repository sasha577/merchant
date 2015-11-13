package org.thoughtworks.assessment.merchant.romannumerals.factory;

import java.util.List;

import org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.romannumerals.impl.converter.RomanNumeralsConverterImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.StateGraphComputer;

/**
 * <p>RomanNumeralsConverterFactory class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class RomanNumeralsConverterFactory{

    /**
     * <p>create.</p>
     *
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.RomanNumeralsConverter} object.
     */
    public static RomanNumeralsConverter create(){
        
        final List<State> stateGraph = new StateGraphComputer().create();
        
        return new RomanNumeralsConverterImpl(stateGraph);
        
    }
    
    private RomanNumeralsConverterFactory() {
        // none
    }
}
