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
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.StateFactoryRegistry;
import org.thoughtworks.assessment.merchant.romannumerals.impl.stategraph.impl.interfaces.statefactory.StateFactory;

/**
 * Computes the whole state graph of the Roman numerals.
 * 
 * The result graph consist only of 84 states.
 */
public final class StateGraphComputer {

    private final Map<RomanNumberLiteral,StateFactory> factoryByliteral;
    private final List<StateFactory> factories;

    /**
     * Constructor.
     */
    public StateGraphComputer() {

        this.factories = 
                createStateFactories(new StateFactoryRegistryImpl());

        this.factoryByliteral = 
                CollectionUtils.toMap( factories, StateFactory::forLiteral, Function.identity() );

    }

    /**
     * Creates the state graph of the Roman numerals.
     * 
     * @return a list of the initial states of the graph.
     */
    public List<State> create(){

        return CollectionUtils.map( factories, f -> f.apply(Optional.empty()));

    }

    /**
     * Provides the access to the map between the literal and the corresponding state factory.
     */
    private final class StateFactoryRegistryImpl implements StateFactoryRegistry{

        @Override
        public StateFactory getStateFactoryForLiteral(final RomanNumberLiteral literal) {

            return StateGraphComputer.this.factoryByliteral.get(literal);
        }
    }

    /**
     * Creates a collection of state factories, one factory for each Roman numeral literal.
     */
    private static List<StateFactory> createStateFactories( final StateFactoryRegistry stateFactoryRegistry){

        //The symbols "I", "X", "C", and "M" can be repeated three times in succession, but no more.
        //  "V", "L", and "D" can never be subtracted.
        return Arrays.asList(
                // "I" can be subtracted from "V" and "X" only.
                memorize(new StateFactoryImpl(I, 3,  Arrays.asList(V,X), stateFactoryRegistry)),

                memorize(new StateFactoryImpl(V, 1,  Collections.emptyList(), stateFactoryRegistry)),

                // "X" can be subtracted from "L" and "C" only.
                memorize(new StateFactoryImpl(X, 3,  Arrays.asList(L,C), stateFactoryRegistry)),

                memorize(new StateFactoryImpl(L, 1,  Collections.emptyList(), stateFactoryRegistry)),

                //  "C" can be subtracted from "D" and "M" only.
                memorize(new StateFactoryImpl(C, 3,  Arrays.asList(D,M), stateFactoryRegistry)),

                memorize(new StateFactoryImpl(D, 1,  Collections.emptyList(), stateFactoryRegistry)),
                memorize(new StateFactoryImpl(M, 3,  Collections.emptyList(), stateFactoryRegistry))
                );
    }

    /**
     * Wrappers the state factory instance with the caching capability.
     *
     * Because StateFactory is state less and the result depends exclusively on the input parameter, 
     * it possible to cache the result. 
     * 
     * @param stateFatory the factory to wrap.
     * @return the factory with caching capability.
     */
    private static StateFactory memorize(final StateFactory stateFatory) {

        final Map<Optional<RomanNumberLiteral>,State> cashe = new ConcurrentHashMap<>();

        return new StateFactory() {

            @Override
            public State apply(final Optional<RomanNumberLiteral> t) {
                return cashe.computeIfAbsent(t, stateFatory);
            }

            @Override
            public RomanNumberLiteral forLiteral() {
                return stateFatory.forLiteral();
            }
        };
    }

}
