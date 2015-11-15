package org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.LocalNumeralsRegistry;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.processor.impl.services.romannumerals.api.types.literal.RomanNumberLiteral;

/**
 * Keeps the mapping between the local numerals and the Roman numerals.
 */
public final class LocalNumberLiteralsRegistryImpl implements LocalNumeralsRegistry{

	/**
	 * the map between local and Roman literals.
	 */
    private final Map<LocalNumberLiteral,RomanNumberLiteral>  romanByLocalLiteral;

    /**
     * Constructor.
     */
    public LocalNumberLiteralsRegistryImpl() {

        this.romanByLocalLiteral = new HashMap<LocalNumberLiteral, RomanNumberLiteral>();
    }

    /** {@inheritDoc} */
    @Override
    public void registerLocalLiteral(final LocalNumberLiteral localLiteral, final RomanNumberLiteral romanLiteral){

        romanByLocalLiteral.put(localLiteral, romanLiteral);
    }

    /** {@inheritDoc} */
    @Override
    public RomanNumber toRomanNumber(final LocalNumber localNumber) throws UnknownLiteral{

        final Function<LocalNumberLiteral, RomanNumberLiteral> local2roman = p -> {
            final RomanNumberLiteral result = romanByLocalLiteral.get(p);
            return throwExceptionIfNull(p, result);
        };

        try{
            
            return new RomanNumber( CollectionUtils.map(localNumber.getValue(), local2roman ) );
            
        }catch(final _UnknownLiteral ex){
            
            throw new UnknownLiteral(ex.getUnknownLiteral());
        }
    }

    /**
     * check if literal is found and throws _UnknownLiteral exception in other case.
     */
    private static RomanNumberLiteral throwExceptionIfNull(final LocalNumberLiteral p, final RomanNumberLiteral result) {
        if(result != null){
            return result;
        }
        else{
            // because checked exceptions are not allowed in the function we have to tunnel the exception as unchecked.  
            throw new _UnknownLiteral(p);
        }
    }


    /**
     * 
     * Because checked exceptions are not allowed in the function we have to tunnel the exception as unchecked. 
     *
     */
    @SuppressWarnings("serial")
    private static final class _UnknownLiteral extends RuntimeException{

        private final LocalNumberLiteral unknownLiteral;

        private _UnknownLiteral(final LocalNumberLiteral literal){
            super(String.format("unknown literal: %s", literal));
            this.unknownLiteral = literal;
        }

        public LocalNumberLiteral getUnknownLiteral(){
            return unknownLiteral;
        }
    }


}
