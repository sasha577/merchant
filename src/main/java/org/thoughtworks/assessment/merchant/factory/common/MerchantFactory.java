package org.thoughtworks.assessment.merchant.factory.common;

import org.thoughtworks.assessment.merchant.numberregistry.LocalNumberLiteralsRegistryImpl;
import org.thoughtworks.assessment.merchant.processor.Merchant;
import org.thoughtworks.assessment.merchant.productcatalog.ProductCatalogImpl;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

public final class MerchantFactory {

    public static Merchant create() {
        
        return new Merchant(
                new LocalNumberLiteralsRegistryImpl(), 
                RomanNumeralsConverterFactory.create(), 
                new ProductCatalogImpl());
    }

    private MerchantFactory() {
        // NONE
    }
}
