package com.apatel428.a69registration.activities;

import com.apatel428.a69registration.model.IntDate;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by 14bpo on 11/19/2017.
 */


public class IntDateEqualsTest {
    @Test
    public void equalsTest() {
        IntDate intDate = new IntDate(new int[]{11, 17});
        intDate.increment();
        IntDate intDate2 = new IntDate(new int[]{11, 17});
        IntDate intDate3 = new IntDate(new int[]{10, 17});
        Integer integer = new Integer(1711);
        assertEquals(true, intDate.equals(intDate2));
        assertEquals(false, intDate.equals(null));
        assertEquals(false, intDate.equals(intDate3));
        assertEquals(false, intDate.equals(integer));
    }
}
