package org.thoughtworks.assessment.merchant.numberregistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.numberregistry.api.LocalNumberLiteralsRegistry;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

public final class LocalNumberLiteralsRegistryImpl implements LocalNumberLiteralsRegistry{

    private final Map<LocalNumberLiteral,RomanNumberLiteral>  romanByLocalLiteral;

    public LocalNumberLiteralsRegistryImpl() {

        this.romanByLocalLiteral = new HashMap<LocalNumberLiteral, RomanNumberLiteral>();
    }

    /* (non-Javadoc)
     * @see org.thoughtworks.assessment.merchant.numberregistry.LocalNumberLiteralsRegistry#registerLocalLiteral(org.thoughtworks.assessment.merchant.numberregistry.types.LocalNumberLiteral, org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral)
     */
    @Override
    public void registerLocalLiteral(final LocalNumberLiteral localLiteral, final RomanNumberLiteral romanLiteral){

        romanByLocalLiteral.put(localLiteral, romanLiteral);
    }

    /* (non-Javadoc)
     * @see org.thoughtworks.assessment.merchant.numberregistry.LocalNumberLiteralsRegistry#toRomanNumber(org.thoughtworks.assessment.merchant.numberregistry.types.LocalNumber)
     */
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

    private static RomanNumberLiteral throwExceptionIfNull(final LocalNumberLiteral p, final RomanNumberLiteral result) {
        if(result != null){
            return result;
        }
        else{
            // because checked exceptions are not allowed in the function we have to tunnel the exception as unchecked.  
            throw new _UnknownLiteral(p);
        }
    }


    @SuppressWarnings("serial")
    static final class _UnknownLiteral extends RuntimeException{

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
