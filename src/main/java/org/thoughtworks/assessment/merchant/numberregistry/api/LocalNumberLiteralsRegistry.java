package org.thoughtworks.assessment.merchant.numberregistry.api;

import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.LocalNumber;
import org.thoughtworks.assessment.merchant.numberregistry.api.common.types.literal.LocalNumberLiteral;
import org.thoughtworks.assessment.merchant.numberregistry.api.exceptions.UnknownLiteral;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.RomanNumber;
import org.thoughtworks.assessment.merchant.romannumerals.api.common.types.symbols.RomanNumberLiteral;

public interface LocalNumberLiteralsRegistry {

    public void registerLocalLiteral(LocalNumberLiteral localLiteral, RomanNumberLiteral romanLiteral);

    public RomanNumber toRomanNumber(LocalNumber localNumber) throws UnknownLiteral;

}