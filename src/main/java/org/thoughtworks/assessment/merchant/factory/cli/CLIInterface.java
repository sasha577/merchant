package org.thoughtworks.assessment.merchant.factory.cli;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.thoughtworks.assessment.merchant.factory.common.MerchantFactory;
import org.thoughtworks.assessment.merchant.processor.Merchant;

/**
 * Provides the user interface to the program over the command line interface (CLI).
 * 
 * Gets the input data from the system input and puts the program output into the system output.
 */
public final class CLIInterface {

    /**
     * Entry point for the process.
     */
    public static void main(final String[] args) {

        final Merchant merchant = MerchantFactory.create();

        try(
            final Reader in = new InputStreamReader(System.in);
            final Writer out = new OutputStreamWriter(System.out) ){

            merchant.process(in, out);
            
        }
        catch(final IOException e){
            throw new RuntimeException("unexpected problem during IO operation", e);
        }

    }
}
