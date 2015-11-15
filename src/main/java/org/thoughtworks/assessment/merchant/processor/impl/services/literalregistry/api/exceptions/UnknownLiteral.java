package org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.exceptions;

import org.thoughtworks.assessment.merchant.processor.impl.services.literalregistry.api.common.types.literal.LocalNumberLiteral;

/**
 * An exception thrown if the value of an numeral literal is not known. 
 */
@SuppressWarnings("serial")
public final class UnknownLiteral extends Exception{
    
	/**
	 * Constructor.
	 * @param unknownLiteral the literal that causes this exception. 
	 */
	public UnknownLiteral(final LocalNumberLiteral unknownLiteral){
        super(String.format("unknown literal: %s", unknownLiteral.getValue()));
    }

}
