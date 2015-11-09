package org.thoughtworks.assessment.merchant.factory.cli;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import org.thoughtworks.assessment.merchant.factory.common.MerchantFactory;
import org.thoughtworks.assessment.merchant.processor.Merchant;

public final class CLIInterface {

    public static void main(final String[] args) {
        
        final Merchant processor = MerchantFactory.create();
        
        final BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        final BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        
        processor.process(in, out);

    }
}
