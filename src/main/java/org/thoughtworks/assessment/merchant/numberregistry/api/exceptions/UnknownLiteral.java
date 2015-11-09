package org.thoughtworks.assessment.merchant.numberregistry.api.exceptions;

@SuppressWarnings("serial")
public final class UnknownLiteral extends Exception{
    
    public UnknownLiteral(final Exception cause){
        super(cause.getMessage(), cause);
    }
}