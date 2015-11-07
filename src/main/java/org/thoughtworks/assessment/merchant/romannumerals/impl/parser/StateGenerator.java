package org.thoughtworks.assessment.merchant.romannumerals.impl.parser;

import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.C;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.D;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.I;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.L;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.M;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.V;
import static org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral.X;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

public final class StateGenerator{

    private final Map<RomanNumberLiteral,StateFactory> factoryBySymbol;
    private final List<StateFactory> factories;
    
    public StateGenerator() {
        
        this.factories = 
                createStateFactories(new MetaStateFactoryImpl());
        
        this.factoryBySymbol = 
                CollectionUtils.toMap( factories, StateFactory::getSymbol, Function.identity() );
        
    }
    
    public Evaluator generate(){
        
        return new Evaluator(CollectionUtils.map( factories, f -> f.create()));
        
    }
    
    private final class MetaStateFactoryImpl implements MetaStateFactory{
        
        @Override
        public StateFactory getSymbolFactory(final RomanNumberLiteral symbol) {

            return StateGenerator.this.factoryBySymbol.get(symbol);
        }
    }
    
    private static List<StateFactory> createStateFactories( final MetaStateFactory metaStateFactory){
        
        return Arrays.asList( 
                new StateFactory(I, 3,  Arrays.asList(V,X), metaStateFactory),
                new StateFactory(V, 1,  Collections.emptyList(), metaStateFactory),
                new StateFactory(X, 3,  Arrays.asList(L,C), metaStateFactory),
                new StateFactory(L, 1,  Collections.emptyList(), metaStateFactory),
                new StateFactory(C, 3,  Arrays.asList(D,M), metaStateFactory),
                new StateFactory(D, 1,  Collections.emptyList(), metaStateFactory),
                new StateFactory(M, 3,  Collections.emptyList(), metaStateFactory)
                );
    }

}
