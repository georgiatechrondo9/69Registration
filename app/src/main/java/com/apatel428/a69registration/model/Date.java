package com.apatel428.a69registration.model;

/**
 * Created by 14bpo on 11/8/2017.
 */

public class Date {
    private int[] date;
    private int count;

    public Date() {
        this(new int[]{0,0});
    }

    public Date(int[] s) {
        date = s;
        count = 0;
    }

    @Override
    public boolean equals(Object o) {
        return date[0] == (((Date)o).getDate()[0])
                && date[2] == (((Date)o).getDate()[2]);
    }

    public int[] getDate() {
        return date;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int i) {
        count = i;
    }

    public void increment() {
        count++;
    }
}
