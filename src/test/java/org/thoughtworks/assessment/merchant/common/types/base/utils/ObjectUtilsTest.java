package org.thoughtworks.assessment.merchant.common.types.base.utils;

import org.junit.Assert;
import org.junit.Test;

public final class ObjectUtilsTest {

    @Test
    public void areEqual() {
        Assert.assertTrue(ObjectUtils.areEqual(Integer.valueOf(1), Integer.valueOf(1)));
    }

    @Test
    public void nullsAreEqual() {
        Assert.assertTrue(ObjectUtils.areEqual(null, null));
    }

    @Test
    public void notEqual() {
        Assert.assertFalse(ObjectUtils.areEqual(Integer.valueOf(1), null));
    }

    @Test
    public void notEqual2() {
        Assert.assertFalse(ObjectUtils.areEqual(null,Integer.valueOf(1)));
    }

    @Test
    public void notEqual3() {
        Assert.assertFalse(ObjectUtils.areEqual(Integer.valueOf(2),Integer.valueOf(1)));
    }

    @Test
    public void toStringObject() {
        
        Assert.assertEquals("1", ObjectUtils.toString(Integer.valueOf(1)));
    }

    @Test
    public void nullToStringObject() {
        
        Assert.assertEquals("null", ObjectUtils.toString(null));
    }

    @Test
    public void hashCodeNullObject() {
        Assert.assertEquals(0, ObjectUtils.hashCode(null));

    }

    @Test
    public void hashCodeObject() {
        Assert.assertEquals(1, ObjectUtils.hashCode(Integer.valueOf(1)));

    }

    @Test
    public void testCompareNulls() {
        Assert.assertEquals(0,ObjectUtils.compare(null, null));
    }

    @Test
    public void testCompare1() {
        Assert.assertEquals(1,ObjectUtils.compare(Integer.valueOf(1), null));
    }

    @Test
    public void testCompare2() {
        Assert.assertEquals(-1,ObjectUtils.compare(null, Integer.valueOf(1)));
    }

    @Test
    public void testCompare3() {
        Assert.assertEquals(0,ObjectUtils.compare(Integer.valueOf(1), Integer.valueOf(1)));
    }

    @Test
    public void testCompare4() {
        Assert.assertEquals(-1,ObjectUtils.compare(Integer.valueOf(1), Integer.valueOf(2)));
    }

}
