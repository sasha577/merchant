package org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.impl.interfaces.StateFactoryRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

/**
 * Provides StateFactory for particular Roman literal.
 */
public final class StateFactoryImpl implements StateFactory{

    /**
     * The literal for witch this factory creates states. 
     */
    private final RomanNumberLiteral literal;
    
    /**
     * How many times the literal can be repeated in the row  
     */
    private final int maxReccurence;
    
    /**
     * The list of literals this one is allowed to subtract.
     * For example 'I' is allowed to subtract from 'V' and 'X'   
     */
    private final Collection<RomanNumberLiteral> minuends;

    /**
     * The reference to factory registry.
     */
    private final StateFactoryRegistry factoryRegistry;

    /**
     * Constructor.
     *
     * @param symbol the literal for witch this factory creates states.
     * @param maxCount how many times the literal can be repeated in the row.
     * @param minuends the list of literals this one is allowed to subtract.
     * @param factoryRegistry  the reference to factory registry..
     */
    public StateFactoryImpl(
            final RomanNumberLiteral symbol, 
            final int maxCount, 
            final Collection<RomanNumberLiteral> minuends,
            final StateFactoryRegistry factoryRegistry) {

        this.literal = symbol;
        this.maxReccurence = maxCount;
        this.minuends = minuends;
        this.factoryRegistry = factoryRegistry;
    }

    /** {@inheritDoc} */
    @Override
    public RomanNumberLiteral forLiteral(){
        return literal;
    }

    /** {@inheritDoc} */
    @Override
    public State apply(final Optional<RomanNumberLiteral> predecessor) {
        
        final List<State> followers = new ArrayList<State>(RomanNumberLiteral.values().length);
        
        // a previous value subtract from this one.
        final boolean isMinuend = predecessor.isPresent() && literal.isHigherThen(predecessor.get());
        
        // the following addition value must be smaller then a previous subtraction one.
        // XLIV instead of XXXIXV
        final Collection<RomanNumberLiteral> lowerliterals =  
                isMinuend ? 
                        CollectionUtils.filter(literal.getLowerValues(), followingLiteral -> predecessor.get().isHigherThen(followingLiteral)) : 
                        literal.getLowerValues();
        
        final List<State> lowerFollowers = 
                CollectionUtils.map(lowerliterals, followingLiteral -> factoryRegistry.getStateFactoryForLiteral(followingLiteral).apply(Optional.of(literal)));
        
        followers.addAll(lowerFollowers);
        
        if( !isMinuend ){
        
            // the literal can be recurrenced in row is not minuend itself
            followers.addAll(createReccurenceStates(lowerFollowers));
            
            // the literal can be used as subtrahend if is not minuend itself 
            followers.addAll(createMinuendStates(predecessor));
        }
        
        return new State(literal,followers,predecessor);
    }

    /**
     * calculates the following states from which this one is allowed to substract. 
     */
    private Collection<State> createMinuendStates(final Optional<RomanNumberLiteral> predecessor) {
        
        final List<RomanNumberLiteral> possibleMinuends =
                CollectionUtils.filter( minuends, minuend -> ( !predecessor.isPresent() || predecessor.get().isHigherOrEqualThen(minuend)) );

        final Function<RomanNumberLiteral, State> stateFactory = 
                followingLiteral -> factoryRegistry.getStateFactoryForLiteral(followingLiteral).apply(Optional.of(literal));
                
        return CollectionUtils.map(possibleMinuends, stateFactory);
    }

    /**
     * creates states of given literals repeated in the row.
     * 
     * @param followers states defined as the possible followers for the states to create.
     * @return states of given literals repeated in the row.
     */
    private Collection<State> createReccurenceStates(final List<State> followers){
        
        if(maxReccurence > 1){

            State reccurence = new State(literal, followers, Optional.of(literal));

            for(int i = 1; i < maxReccurence; ++i){
                reccurence = new State(literal, CollectionUtils.add(followers,reccurence), Optional.of(literal));
            }
            
            return Collections.singletonList(reccurence);
        }
        else{
            return Collections.emptyList();
        }
    }


}
