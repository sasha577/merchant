package org.thoughtworks.assessment.merchant.romannumerals.impl.common.state;

import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.common.types.base.PairBasedValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * The state node in the state graph of the parser.
 * 
 * The state has particular a value and the list of the possible states can follow it.
 * 
 * The value of the whole Roman number is a sum of all states.
 * 
 * The value of the state depends of the value of the Roman literal this state presents, 
 * but also of the value of the predecessors literal. 
 * Should the value of the predecessor be smaller then this one, the predecessor is considered as subtracter
 * and its value will be compensated.
 * 
 */
@SuppressWarnings("serial")
public final class State extends PairBasedValue<Pair<RomanNumberLiteral,Integer>, Collection<State>>{

    /**
     * Constructor.
     *
     * @param literal a Roman literal this state represents.
     * @param successors a list of allowed succeeding literals.
     * @param predecessor a Roman literal is preceding this one in the state graph.
     */
    public State(final RomanNumberLiteral literal, final Collection<State> successors, final Optional<RomanNumberLiteral> predecessor) {
        super(Pair.of(literal,calculateValue(literal,predecessor)), successors);
    }

    /**
     */
    public RomanNumberLiteral getLiteral() {
        return super.getFirst().getFirstValue();
    }
    
    /**
     * Gets the list of the states or literals that allowed to succeed this one. 
     */
    public Collection<State> getSuccessors() {
        return super.getSecond();
    }
    
    /**
     * Gets the value of the state.
     */
    public int getValue(){
        return super.getFirst().getSecondValue();
    }
    
    /**
     * Calculates the value of the state depending of the literal of this state and the predecessor literal.
     * 
     * @param literal the literal of this state.
     * @param predecessor the literal predecessors this state. 
     * @return the value of the state.
     */
    private static int calculateValue(final RomanNumberLiteral literal, final Optional<RomanNumberLiteral> predecessor ){
        
        if(predecessor.isPresent() && literal.isHigherThen(predecessor.get())){
            return literal.getValue() - (predecessor.get().getValue() * 2);
        }else{
            return literal.getValue();
        }
    }
}
