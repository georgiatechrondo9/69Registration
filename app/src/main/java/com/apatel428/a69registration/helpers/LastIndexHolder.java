package com.apatel428.a69registration.helpers;

/**
 * Created by 14bpo on 12/2/2017.
 */

public class LastIndexHolder {
    private static long lastIndex;

    public LastIndexHolder(){};

    public static void setLastIndex(long index){
        lastIndex = index;
    }

    public static long getLastIndex() {
        return lastIndex;
    }
}
