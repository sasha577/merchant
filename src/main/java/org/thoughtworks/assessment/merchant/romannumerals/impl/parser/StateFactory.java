package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

public final class StateFactory {

    private final RomanNumberLiteral literal;
    private final int maxReccurence;
    private final Collection<RomanNumberLiteral> minuends;

    private final MetaStateFactory metaFactory;

    public StateFactory(
            final RomanNumberLiteral symbol, 
            final int maxCount, 
            final Collection<RomanNumberLiteral> minuends,
            final MetaStateFactory metaFactory) {

        this.literal = symbol;
        this.maxReccurence = maxCount;
        this.minuends = minuends;
        this.metaFactory = metaFactory;
    }

    public RomanNumberLiteral getSymbol(){
        return literal;
    }

    public State create(){
        return create(Optional.empty());
    }
    
    private State create(final Optional<RomanNumberLiteral> predecessor){

        final List<State> followers = new ArrayList<State>(RomanNumberLiteral.values().length);

        // a previous value subtract from this one.
        final boolean isMinuend = predecessor.isPresent() && literal.isHigherThen(predecessor.get());
        
        // the following addition value must be smaller then a previous subtraction one.
        // XLIV instead of XXXIXV
        final Collection<RomanNumberLiteral> lowerliterals = 
                isMinuend ? CollectionUtils.filter(literal.getLowerValues(), follower -> predecessor.get().isHigherThen(follower)) : literal.getLowerValues();

        final List<State> lowerFollowers = 
                CollectionUtils.map(lowerliterals, p -> metaFactory.getSymbolFactory(p).create(Optional.of(literal)));

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
                follower -> metaFactory.getSymbolFactory(follower).create(Optional.of(literal));
                
        return CollectionUtils.map(possibleMinuends, stateFactory);
    }

    /**
     *  
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
