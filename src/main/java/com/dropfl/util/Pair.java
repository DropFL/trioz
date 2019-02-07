package com.dropfl.util;

public class Pair <T> {
    private T first, second;

    /**
     * initializes two factors to {@code null}.
     */
    public Pair () {
        this(null, null);
    }

    /**
     * @param p target pair to copy. this constructor uses shallow copy.
     */
    public Pair (Pair<T> p) {
        this(p.first, p.second);
    }

    /**
     * 
     * @param first     first factor of this pair
     * @param second    second factor of this pair
     */
    public Pair (T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T first () { return first; }
    public T second () { return second; }

    public void first (T value) { first = value; }
    public void second (T value) { second = value; }

    // for readability
    public T x () { return first; } 
    public T y () { return second; }
    
    public void x (T value) { first = value; }
    public void y (T value) { second = value; }

    @Override
    public String toString() {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}