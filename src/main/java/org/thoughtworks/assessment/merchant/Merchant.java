package org.thoughtworks.assessment.merchant;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.thoughtworks.assessment.merchant.numberregistry.LocalNumberLiteralsRegistryImpl;
import org.thoughtworks.assessment.merchant.processor.Processor;
import org.thoughtworks.assessment.merchant.productcatalog.ProductCatalogImpl;
import org.thoughtworks.assessment.merchant.romannumerals.factory.RomanNumeralsConverterFactory;

public final class Merchant {

    public static void main(final String[] args) {
        
        final Processor processor = 
                new Processor(
                        new LocalNumberLiteralsRegistryImpl(), 
                        RomanNumeralsConverterFactory.create(), 
                        new ProductCatalogImpl());
        
        final Scanner in = new Scanner(System.in);
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        processor.process(in, out);

    }

}
