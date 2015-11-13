package org.thoughtworks.assessment.merchant.factory.cli.data;

import org.thoughtworks.assessment.merchant.common.test.utils.ResourceLoader;

public final class TestResources {

    public static final ResourceLoader INPUT = 
            new ResourceLoader(TestResources.class, "input.txt");
    
    public static final ResourceLoader EXPECTED_OUTPUT = 
            new ResourceLoader(TestResources.class, "expected.output.txt");
    
    private TestResources() {
        // NONE
    }
}
