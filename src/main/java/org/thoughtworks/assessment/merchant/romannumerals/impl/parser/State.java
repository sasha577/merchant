package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.common.types.base.AbstractPair;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

@SuppressWarnings("serial")
public final class State extends AbstractPair<Pair<RomanNumberLiteral,Integer>, Collection<State>>{

    public static int COUNTER = 0;
    
    public State(final RomanNumberLiteral literal, final Collection<State> successors, final Optional<RomanNumberLiteral> predecessor) {
        super(Pair.make(literal,calculateSubstractionCompensation(literal,predecessor)), successors);
        ++COUNTER;
    }

    public RomanNumberLiteral getLiteral() {
        return super.getFirst().getFirstValue();
    }
    
    public Collection<State> getSuccessors() {
        return super.getSecond();
    }
    
    public int getValue(){
        final int value = getLiteral().getValue();
        return value + getSubstractionCompensation();
    }
    
    private int getSubstractionCompensation(){
        return super.getFirst().getSecondValue();
    }
    
    private static int calculateSubstractionCompensation(final RomanNumberLiteral literal, final Optional<RomanNumberLiteral> predecessor ){
        
        if(predecessor.isPresent() && literal.isHigherThen(predecessor.get())){
            return -(predecessor.get().getValue() * 2);
        }else{
            return 0;
        }
    }
}
