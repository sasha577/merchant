package org.thoughtworks.assessment.merchant.common.test.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public final class IOUtils {

    public static String toString(final InputStream input) throws IOException {
        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(input))) {
            final String result = buffer.lines().collect(Collectors.joining("\n"));
            return result;//.substring(0, result.length()-1);
        }
    }

    private IOUtils() {
        // NONE
    }
}
