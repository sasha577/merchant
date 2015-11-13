package org.thoughtworks.assessment.merchant.factory.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.thoughtworks.assessment.merchant.factory.common.MerchantFactory;
import org.thoughtworks.assessment.merchant.processor.Merchant;

/**
 * <p>CLIInterface class.</p>
 *
 * @author arubinov
 * @version $Id: $Id
 */
public final class CLIInterface {

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     */
    public static void main(final String[] args) {

        final Merchant merchant = MerchantFactory.create();

        try(
            final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out)) ){

            merchant.process(in, out);
            
        }
        catch(final IOException e){
            throw new RuntimeException("unexpected problem during io operation", e);
        }

    }
}
