package org.thoughtworks.assessment.merchant.numberregistry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.thoughtworks.assessment.merchant.common.collections.CollectionUtils;
import org.thoughtworks.assessment.merchant.numberregistry.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.types.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.types.symbols.RomanNumberLiteral;

public final class NumberLiteralsTranslator{

    @SuppressWarnings("serial")
    public static final class UnknownLiteral extends Exception{
        private UnknownLiteral(final _UnknownLiteral cause){
            super(cause.getMessage(), cause);
        }
    }

    private final Map<LocalNumberLiteral,RomanNumberLiteral>  romanByLocalLiteral;

    public NumberLiteralsTranslator() {

        this.romanByLocalLiteral = new HashMap<LocalNumberLiteral, RomanNumberLiteral>();
    }

    public void registerLocalLiteral(final LocalNumberLiteral localLiteral, final RomanNumberLiteral romanLiteral){

        romanByLocalLiteral.put(localLiteral, romanLiteral);
    }

    public RomanNumber toRomanNumber(final LocalNumber localNumber) throws UnknownLiteral{

        final Function<LocalNumberLiteral, RomanNumberLiteral> local2roman = p -> {
            final RomanNumberLiteral result = romanByLocalLiteral.get(p);
            return throwExceptionIfNull(p, result);
        };

        try{
            
            return new RomanNumber( CollectionUtils.map(localNumber.getValue(), local2roman ) );
            
        }catch(final _UnknownLiteral ex){
            
            throw new UnknownLiteral(ex);
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
    private static final class _UnknownLiteral extends RuntimeException{

        private final LocalNumberLiteral unknownLiteral;

        private _UnknownLiteral(final LocalNumberLiteral literal){
            super(String.format("unknown literal: %s", literal));
            this.unknownLiteral = literal;
        }

        @SuppressWarnings("unused")
        public LocalNumberLiteral getUnknownLiteral(){
            return unknownLiteral;
        }
    }


}
