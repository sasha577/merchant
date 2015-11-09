package org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph;

import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.C;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.D;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.I;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.L;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.M;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.V;
import static org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral.X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.common.state.State;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.StateFactoryImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.MetaStateFactory;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

public final class StateGraphComputer {

    private final Map<RomanNumberLiteral,StateFactory> factoryBySymbol;
    private final List<StateFactory> factories;
    
    public StateGraphComputer() {
        
        this.factories = 
                createStateFactories(new MetaStateFactoryImpl());
        
        this.factoryBySymbol = 
                CollectionUtils.toMap( factories, StateFactory::getSymbol, Function.identity() );
        
    }
    
    public List<State> create(){
        
        return CollectionUtils.map( factories, f -> f.apply(Optional.empty()));
        
    }
    
    private final class MetaStateFactoryImpl implements MetaStateFactory{
        
        @Override
        public StateFactory getSymbolFactory(final RomanNumberLiteral symbol) {

            return StateGraphComputer.this.factoryBySymbol.get(symbol);
        }
    }
    
    private static List<StateFactory> createStateFactories( final MetaStateFactory metaStateFactory){
        
        //The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
        //  "V", "L", and "D" can never be subtracted.
        return Arrays.asList(
                // "I" can be subtracted from "V" and "X" only.
                memoize(new StateFactoryImpl(I, 3,  Arrays.asList(V,X), metaStateFactory)),
                
                memoize(new StateFactoryImpl(V, 1,  Collections.emptyList(), metaStateFactory)),
                
                // "X" can be subtracted from "L" and "C" only.
                memoize(new StateFactoryImpl(X, 3,  Arrays.asList(L,C), metaStateFactory)),
                
                memoize(new StateFactoryImpl(L, 1,  Collections.emptyList(), metaStateFactory)),
                
                //  "C" can be subtracted from "D" and "M" only.
                memoize(new StateFactoryImpl(C, 3,  Arrays.asList(D,M), metaStateFactory)),
                
                memoize(new StateFactoryImpl(D, 1,  Collections.emptyList(), metaStateFactory)),
                memoize(new StateFactoryImpl(M, 3,  Collections.emptyList(), metaStateFactory))
                );
    }

    // Lambda memorization
    private static StateFactory memoize(final StateFactory f) {
        
        final Map<Optional<RomanNumberLiteral>,State> lookup = new ConcurrentHashMap<>();
        
        return new StateFactory() {
            
            @Override
            public State apply(final Optional<RomanNumberLiteral> t) {
                return lookup.computeIfAbsent(t, f);
            }
            
            @Override
            public RomanNumberLiteral getSymbol() {
                return f.getSymbol();
            }
        };
    }

}
