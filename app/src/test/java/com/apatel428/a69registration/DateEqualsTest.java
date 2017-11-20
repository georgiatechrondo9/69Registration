package com.apatel428.a69registration;

import com.apatel428.a69registration.model.Date;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by 14bpo on 11/19/2017.
 */


public class DateEqualsTest {
    @Test
    public void equalsTest() {
        Date date = new Date(new int[]{11, 17});
        date.increment();
        Date date2 = new Date(new int[]{11, 17});
        Date date3 = new Date(new int[]{10, 17});
        Integer integer = new Integer(1711);
        assertEquals(true, date.equals(date2));
        assertEquals(false, date.equals(null));
        assertEquals(false, date.equals(date3));
        assertEquals(false, date.equals(integer));
    }
}
