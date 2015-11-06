package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberSymbol;

public final class StateFactory {

    private final RomanNumberSymbol symbol;
    private final int maxReccurence;
    private final Collection<RomanNumberSymbol> minuends;

    private final MetaStateFactory metaFactory;

    public StateFactory(
            final RomanNumberSymbol symbol, 
            final int maxCount, 
            final Collection<RomanNumberSymbol> minuends,
            final MetaStateFactory metaFactory) {

        this.symbol = symbol;
        this.maxReccurence = maxCount;
        this.minuends = minuends;
        this.metaFactory = metaFactory;
    }

    public RomanNumberSymbol getSymbol(){
        return symbol;
    }

    public State create(final List<RomanNumberSymbol> processedString, final int substractionCompensation){

        final List<RomanNumberSymbol> actualString = 
                CollectionUtils.add(processedString,symbol);

        final List<State> followers = new ArrayList<State>(RomanNumberSymbol.values().length);

        final Optional<RomanNumberSymbol> predecessor = 
                CollectionUtils.getLast(processedString);

        // a previous value subtract from this one.
        final boolean isMinuend = (substractionCompensation != 0);
        
        // the following addition value must be smaller then a previous subtraction value.
        // XLIV instead of XXXIXV
        final Collection<RomanNumberSymbol> lowerValues = 
                isMinuend ? CollectionUtils.filter(symbol.getLowerValues(), s -> predecessor.get().isHigherThen(s)) : symbol.getLowerValues();

        final List<State> lowerFollowers = 
                CollectionUtils.map(lowerValues, p -> metaFactory.getSymbolFactory(p).create(actualString,0));

        followers.addAll(lowerFollowers);

        if( !isMinuend ){

            followers.addAll(createReccurenceStates(lowerFollowers));
            
            followers.addAll(createSubstractionStates(actualString, predecessor));
        }

        return new State(symbol,followers,substractionCompensation);
    }

    /**
     * calculates the following states from which this one is allowed to substract. 
     */
    private Collection<State> createSubstractionStates(
            final List<RomanNumberSymbol> actualString, final Optional<RomanNumberSymbol> predecessor) {
        
        final List<RomanNumberSymbol> possibleMinuends =
                CollectionUtils.filter( minuends, minuend -> ( !predecessor.isPresent() || predecessor.get().isHigherOrEqualThen(minuend)) );

        final int substractionCompensation = -(symbol.getValue()*2);

        final Function<RomanNumberSymbol, State> minuendStateFactory = 
                minuend -> metaFactory.getSymbolFactory(minuend).create(actualString, substractionCompensation);
                
        return CollectionUtils.map(possibleMinuends, minuendStateFactory);
    }

    /**
     *  
     */
    private Collection<State> createReccurenceStates(final List<State> followers){
        
        if(maxReccurence > 1){

            State reccurence = new State(symbol, followers, 0);

            for(int i = 1; i < maxReccurence; ++i){
                reccurence = new State(symbol, CollectionUtils.add(followers,reccurence), 0);
            }
            
            return Collections.singletonList(reccurence);
        }
        else{
            return Collections.emptyList();
        }
    }

}
