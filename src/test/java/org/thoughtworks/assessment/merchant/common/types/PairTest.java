package org.thoughtworks.assessment.merchant.common.types;

import org.junit.Assert;
import org.junit.Test;

public final class PairTest {

    @Test
    public void equals() {
        
        Assert.assertEquals(Pair.of(1, "3"),Pair.of(1, "3"));
    }

    @Test
    public void getFirst() {
        
        Assert.assertEquals(Integer.valueOf(1),Pair.of(1, "3").getFirstValue());
    }

    @Test
    public void getSecond() {
        
        Assert.assertEquals("3",Pair.of(1, "3").getSecondValue());
    }
    
    @Test
    public void testHashCode() {
        
        Assert.assertEquals(Pair.of(1, "3").hashCode(),Pair.of(1, "3").hashCode());
    }

    @Test
    public void testToString() {
        
        Assert.assertEquals(Pair.of(1, "3").toString(),"Pair[1,3]");
    }
}
