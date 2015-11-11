package org.thoughtworks.assessment.merchant.numberregistry.api.exceptions;

import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;

@SuppressWarnings("serial")
public final class UnknownLiteral extends Exception{
    
    public UnknownLiteral(final LocalNumberLiteral literal){
        super(String.format("unknown literal: %s", literal.getValue()));
    }

}