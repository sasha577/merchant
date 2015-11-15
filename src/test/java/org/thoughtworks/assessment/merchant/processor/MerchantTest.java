package org.thoughtworks.assessment.merchant.processor;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.thoughtworks.assessment.merchant.processor.common.types.Reply;
import org.thoughtworks.assessment.merchant.processor.common.types.Request;


public final class MerchantTest {

    @Test
    public void testProcess() throws Exception {
        
    	final Merchant merchant = new Merchant();
    	
    	merchant.process(new Request("bock is I"));
    	
    	final Optional<Reply> actual = merchant.process(new Request("how much is bock bock ?"));
    	
    	Assert.assertEquals(new Reply("bock bock is 2"), actual.get());
    }
}
