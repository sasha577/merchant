package org.thoughtworks.assessment.merchant.processor;

import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.common.test.utils.IOUtils;
import org.thoughtworks.assessment.merchant.factory.common.MerchantFactory;
import org.thoughtworks.assessment.merchant.processor.data.TestResources;


public final class MerchantTest {

    @Test
    public void testProcess() throws Exception {
        
        final Reader in = new InputStreamReader(TestResources.INPUT.getResource());
        
        final ByteArrayOutputStream actual = new ByteArrayOutputStream();
        
        final Writer out = new OutputStreamWriter(actual);

        MerchantFactory.create().process(in, out);
        
        Assert.assertEquals(IOUtils.toString(TestResources.EXPECTED_OUTPUT.getResource()), actual.toString());
    }
}
