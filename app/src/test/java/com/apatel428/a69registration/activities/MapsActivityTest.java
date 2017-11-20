package com.apatel428.a69registration.activities;

import org.junit.Test;
import static org.junit.Assert.*;

public class MapsActivityTest {

    @Test
    public void testStringToIntArray() {
        Object object1 = "1-2-3";
        Object object2 = "4-5-6";
        int[] test1 = MapsActivity.stringToIntArray(object1);
        int[] test2 = MapsActivity.stringToIntArray(object2);
        assertEquals(1,test1[0]);
        assertEquals(2,test1[1]);
        assertEquals(3,test1[2]);
        assertEquals(4,test2[0]);
        assertEquals(5,test2[1]);
        assertEquals(6,test2[2]);

    }
}