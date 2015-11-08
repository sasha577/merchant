package org.thoughtworks.assessment.merchant.romannumerals.factory;

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
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.impl.RomanNumeralsConverterImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.MetaStateFactory;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.State;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.StateFactoryImpl;
import org.thoughtworks.assessment.merchant.romannumerals.impl.parser.interfaces.StateFactory;

public final class RomanNumeralsConverterFactory{

    private final Map<RomanNumberLiteral,StateFactory> factoryBySymbol;
    private final List<StateFactory> factories;
    
    public RomanNumeralsConverterFactory() {
        
        this.factories = 
                createStateFactories(new MetaStateFactoryImpl());
        
        this.factoryBySymbol = 
                CollectionUtils.toMap( factories, StateFactory::getSymbol, Function.identity() );
        
    }
    
    public RomanNumeralsConverterImpl create(){
        
        return new RomanNumeralsConverterImpl(CollectionUtils.map( factories, f -> f.apply(Optional.empty())));
        
    }
    
    private final class MetaStateFactoryImpl implements MetaStateFactory{
        
        @Override
        public StateFactory getSymbolFactory(final RomanNumberLiteral symbol) {

            return RomanNumeralsConverterFactory.this.factoryBySymbol.get(symbol);
        }
    }
    
    private static List<StateFactory> createStateFactories( final MetaStateFactory metaStateFactory){
        
        return Arrays.asList( 
                memoize(new StateFactoryImpl(I, 3,  Arrays.asList(V,X), metaStateFactory)),
                memoize(new StateFactoryImpl(V, 1,  Collections.emptyList(), metaStateFactory)),
                memoize(new StateFactoryImpl(X, 3,  Arrays.asList(L,C), metaStateFactory)),
                memoize(new StateFactoryImpl(L, 1,  Collections.emptyList(), metaStateFactory)),
                memoize(new StateFactoryImpl(C, 3,  Arrays.asList(D,M), metaStateFactory)),
                memoize(new StateFactoryImpl(D, 1,  Collections.emptyList(), metaStateFactory)),
                memoize(new StateFactoryImpl(M, 3,  Collections.emptyList(), metaStateFactory))
                );
    }

    // Lambda memorization
    public static StateFactory memoize(final StateFactory f) {
        
        final ConcurrentMap<Optional<RomanNumberLiteral>,State> lookup = new ConcurrentHashMap<>();
        
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
