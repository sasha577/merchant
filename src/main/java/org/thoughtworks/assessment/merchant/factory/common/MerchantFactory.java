package org.thoughtworks.assessment.merchant.factory.common;

import org.thoughtworks.assessment.merchant.numberregistry.LocalNumberLiteralsRegistryImpl;
import org.thoughtworks.assessment.merchant.processor.Merchant;
import org.thoughtworks.assessment.merchant.productcatalog.ProductCatalogImpl;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

/**
 * <p>MerchantFactory class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class MerchantFactory {

    /**
     * <p>create.</p>
     *
     * @return a {@link org.thoughtworks.assessment.merchant.processor.Merchant} object.
     */
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
