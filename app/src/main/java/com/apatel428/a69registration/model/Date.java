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
    public int hashCode() {
        return date[1]*100 + date[0];
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (o.getClass().getSimpleName()
                .equals(this.getClass().getSimpleName())) {
            return false;
        } else {
            return o.hashCode() == this.hashCode();
        }
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
