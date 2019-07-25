package com.dropfl.util;

import java.util.AbstractList;

import com.google.common.base.Preconditions;

/**
 * {@code Pair<T>} is a 2D tuple of the same type {@code T}. This class is like
 * a {@code T}-type array with fixed size with 2.
 */
public class Pair<T> extends AbstractList<T>
{
    protected T first;
    protected T second;

    /**
     * initializes two factors to {@code null}.
     */
    public Pair()
    {
        this(null, null);
    }

    /**
     * creates a copy from other pair.
     * <p>
     * <strong>WARNING</strong>: this constructor uses shallow copy. It may cause
     * some unexpected behavior, so be careful on use.
     * 
     * @param p target pair to copy.
     * @see #clone()
     */
    public Pair(Pair<T> p)
    {
        this(p.first, p.second);
    }

    /**
     * creates a pair with given arguments.
     * <p>
     * <strong>WARNING</strong>: this constructor uses shallow copy. It may cause
     * some unexpected behavior, so be careful on use.
     * 
     * @param first  first factor of this pair
     * @param second second factor of this pair
     */
    public Pair(T first, T second)
    {
        this.first = first;
        this.second = second;
    }

    /**
     * @return the first factor of this pair
     */
    public T first()
    {
        return first;
    }

    /**
     * @return the second factor of this pair
     */
    public T second()
    {
        return second;
    }

    /**
     * makes a copy from this {@code Pair}.
     * <p>
     * <strong>WARNING</strong>: this constructor uses shallow copy. It may cause
     * some unexpected behavior, so be careful on use.
     * 
     * @return copy of this object.
     * @see #Pair(Pair)
     */
    public Pair<T> clone()
    {
        return new Pair<>(this);
    }

    /**
     * changes the first factor of this pair.
     * <p>
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive
     * data. It may cause some unexpected behavior, so be careful on use.
     * 
     * @param value object to set to the first factor
     */
    public void first(T value)
    {
        first = value;
    }

    /**
     * changes the second factor of this pair.
     * <p>
     * <strong>WARNING</strong>: {@code Pair} has object factors; not primitive
     * data. It may cause some unexpected behavior, so be careful on use.
     * 
     * @param value object to set to the second factor
     */
    public void second(T value)
    {
        second = value;
    }

    @Override
    public String toString()
    {
        return "(" + first + ", " + second + ")";
    }

    // List compatibility
    
    @Override
    public T get(int index)
    {
        Preconditions.checkPositionIndex(index, 1);
        
        return index == 0 ? first : second;
    }

    @Override
    public T set(int index, T element)
    {
        Preconditions.checkPositionIndex(index, 1);

        T previous = get(index);

        if (index == 0)
            this.first = element;
        else
            this.second = element;

        return previous;
    }
    
    @Override
    public int size()
    {
        return 2;
    }
}