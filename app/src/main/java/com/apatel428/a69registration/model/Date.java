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

    /**
     * Constructor
     * @param s integer array of {month, year}
     */
    public Date(int[] s) {
        date = s;
        count = 1;
    }

    /**
     * hash function for Date object
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        return date[1]*100 + date[0];
    }

    /**
     * equals method for Date object
     *
     * @param o an object
     * @return if the object is equal to this Date
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else {
            return this.hashCode() == o.hashCode()
                    && this.getClass().getName()
                    == o.getClass().getName();
        }
    }

    /**
     * Gets date as an array of integers
     *
     * @return date
     */
    public int[] getDate() {
        return date;
    }

    /**
     * Gets the count of this date
     *
     * @return count
     */
    public int getCount() {
        return count;
    }

    /**
     * Sets the count for this date
     *
     * @param i a count to set
     */
    public void setCount(int i) {
        count = i;
    }

    /**
     * Increments the count of this Date by 1
     */
    public void increment() {
        count++;
    }
}
