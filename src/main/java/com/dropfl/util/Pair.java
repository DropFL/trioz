package com.dropfl.util;

/**
 * {@code Pair<T>} is a 2D tuple of the same type {@code T}.
 * This class is like a {@code T}-type array with 2 elements.
 */
public class Pair<T> {

    private T first, second;

    /**
     * initializes two factors to {@code null}.
     */
    public Pair () {
        this(null, null);
    }

    /**
     * creates a copy from other pair
     * <strong>WARNING</strong>: this constructor uses shallow copy.
     * It may cause some unexpected behavior, so be careful on use.
     * 
     * @param p target pair to copy.
     * @see #clone()
     */
    public Pair (Pair<T> p) {
        this(p.first, p.second);
    }

    /**
     * creates a pair with given arguments.
     * <strong>WARNING</strong>: this constructor uses shallow copy.
     * It may cause some unexpected behavior, so be careful on use.
     * 
     * @param first first factor of this pair
     * @param second second factor of this pair
     */
    public Pair (T first, T second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first factor of this pair
     */
    public T first () { return first; }

    /**
     * @return the second factor of this pair
     */
    public T second () { return second; }

    /**
     * Like {@link java.util.List#get(int)}, returns an element corresponds to {@code index}.
     * Since {@code Pair} has only two elements, its maximal index is 1.
     * 
     * @return {@link #first()} or {@link #second()} when {@code index} is 0 or 1, respectively.
     * @throws IndexOutOfBoundsException if {@code index} is negative, or exceeds 1.
     */
    public T get (int index) {
        if (index < 0) 
            throw new IndexOutOfBoundsException("Negative index is not accepted.");
        if (index == 0)
            return first;
        if (index == 1)
            return second;
        
        throw new IndexOutOfBoundsException(index + "exceeds 1; it is out of range.");
    }

    /**
     * makes a copy from this {@code Pair}.
     * <strong>WARNING</strong>: this constructor uses shallow copy.
     * It may cause some unexpected behavior, so be careful on use.
     * 
     * @return copy of this object.
     * @see #Pair(Pair)
     */
    public Pair<T> clone() { return new Pair<>(this); }
    
    /**
     * changes the first factor of this pair
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive data.
     * It may cause some unexpected behavior, so be careful on use.
     * 
     * @param value object to set to the first factor
     */
    public void first (T value) { first = value; }

    /**
     * changes the second factor of this pair
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive data.
     * It may cause some unexpected behavior, so be careful on use.
     * 
     * @param value object to set to the second factor
     */
    public void second (T value) { second = value; }

    @Override
    public String toString () {
        return "(" + first.toString() + ", " + second.toString() + ")";
    }
}