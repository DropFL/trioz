package com.dropfl.util;

public class Pair<T> {

    private T first;
    private T second;

    /**
     * initializes two factors to {@code null}.
     */
    public Pair () {
        this(null, null);
    }

    /**
     * creates a copy from other pair
     * <strong>WARNING</strong>: this constructor uses shallow copy. It may cause some unexpected behavior, so be careful on use.
     * @param p target pair to copy.
     */
    public Pair (Pair<T> p) {
        this(p.first, p.second);
    }

    /**
     * @param first first factor of this pair
     * @param second second factor of this pair
     */
    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first factor of this pair
     */
    public T first() { return first; }

    /**
     * @return the second factor of this pair
     */
    public T second() { return second; }

    
    /**
     * changes the first factor of this pair
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive data. It may cause some unexpected behavior, so be careful on use.
     * @param value object to set to the first factor
     */
    public void first(T value) { first = value; }

    /**
     * changes the second factor of this pair
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive data. It may cause some unexpected behavior, so be careful on use.
     * @param value object to set to the second factor
     */
    public void second(T value) { second = value; }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}