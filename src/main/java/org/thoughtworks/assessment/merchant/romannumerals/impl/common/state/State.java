package org.thoughtworks.assessment.merchant.romannumerals.impl.common.state;

import java.util.Collection;
import java.util.Optional;

import org.thoughtworks.assessment.merchant.common.types.Pair;
import org.thoughtworks.assessment.merchant.common.types.base.PairBasedValue;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

/**
 * <p>State class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
@SuppressWarnings("serial")
public final class State extends PairBasedValue<Pair<RomanNumberLiteral,Integer>, Collection<State>>{

    /**
     * <p>Constructor for State.</p>
     *
     * @param literal a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     * @param successors a {@link java.util.Collection} object.
     * @param predecessor a {@link java.util.Optional} object.
     */
    public State(final RomanNumberLiteral literal, final Collection<State> successors, final Optional<RomanNumberLiteral> predecessor) {
        super(Pair.of(literal,calculateSubstractionCompensation(literal,predecessor)), successors);
    }

    /**
     * <p>getLiteral.</p>
     *
     * @return a {@link org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral} object.
     */
    public RomanNumberLiteral getLiteral() {
        return super.getFirst().getFirstValue();
    }
    
    /**
     * <p>getSuccessors.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<State> getSuccessors() {
        return super.getSecond();
    }
    
    /**
     * <p>getValue.</p>
     *
     * @return a int.
     */
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
