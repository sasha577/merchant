package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import java.util.Collection;

import org.thoughtworks.assessment.merchant.common.types.AbstractPair;
import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberSymbol;

@SuppressWarnings("serial")
public final class State extends AbstractPair<Pair<RomanNumberSymbol,Integer>, Collection<State>>{

    public static int COUNTER = 0;
    
    public State(final RomanNumberSymbol symbol, final Collection<State> successors, final int substractionCompensation) {
        super(Pair.make(symbol,substractionCompensation), successors);
        ++COUNTER;
    }

    public RomanNumberSymbol getSymbol() {
        return super.getFirst().getFirstValue();
    }
    
    public Collection<State> getSuccessors() {
        return super.getSecond();
    }
    
    public int getValue(){
        final int value = getSymbol().getValue();
        return value + getSubstractionCompensation();
    }
    
    private int getSubstractionCompensation(){
        return super.getFirst().getSecondValue();
    }
}
