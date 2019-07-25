package com.dropfl.util;

import com.google.common.base.Preconditions;

/**
 * {@code Point} object represents a 2D point or a 2D vector. This class also
 * defines some common operations (e.g. {@link #rotate(double)},
 * {@link #dot(Point)}) and transformation from Number-based {@link Pair} to
 * {@code Point} and vice versa.
 * 
 * @see Pair
 */
public class Point extends Pair<Double>
{
    /**
     * constructs a {@code Point} object represents the origin point.
     */
    public Point()
    {
        this(0, 0);
    }

    /**
     * constructs a {@code Point} object.
     * 
     * @param x value of {@code x}.
     * @param y value of {@code y}.
     */
    public Point(double x, double y)
    {
        this.first = x;
        this.second = y;
    }

    /**
     * constructs a {@code Point} object from a x-y pair.
     * 
     * @param pair {@code Pair} object that contains {@code x} and {@code y} values.
     */
    public Point(Pair<? extends Number> pair)
    {
        this(pair.first().doubleValue(), pair.second().doubleValue());
    }

    /**
     * makes copy of {@code point}.
     * 
     * @param point {@code Point} object to copy.
     */
    public Point(Point point)
    {
        this(point.first, point.second);
    }

    /**
     * gets {@code x} value.
     * 
     * @return {@code x} value of this point.
     */
    public double x()
    {
        return first;
    }

    /**
     * gets {@code y} value.
     * 
     * @return {@code y} value of this point.
     */
    public double y()
    {
        return second;
    }

    /**
     * modifies {@code x} value.
     * 
     * @param value value for {@code x}.
     */
    public void x(double value)
    {
        this.first = value;
    }

    /**
     * modifies {@code y} value.
     * 
     * @param value value for {@code y}.
     */
    public void y(double value)
    {
        this.second = value;
    }

    /**
     * adds {@code x} and {@code y} values from {@code point}.
     * 
     * @param point other {@code Point} to add.
     * @return callee itself, after addition.
     */
    public Point add(Point point)
    {
        return add(point.x(), point.y());
    }

    /**
     * adds {@code x} and {@code y} values from {@code pair}.
     * 
     * @param pair {@code Pair} contains x-y pair to add.
     * @return callee itself, after addition.
     */
    public Point add(Pair<? extends Number> pair)
    {
        return add(pair.first().doubleValue(), pair.second().doubleValue());
    }

    /**
     * adds {@code dx} and {@code dy} to {@code x} and {@code y}.
     * 
     * @param dx value for x to add.
     * @param dy value for y to add.
     * @return callee itself, after addition.
     */
    public Point add(double dx, double dy)
    {
        first += dx;
        second += dy;

        return this;
    }

    /**
     * subtracts {@code x} and {@code y} values from {@code point}.
     * 
     * @param point other {@code Point} to subtract.
     * @return callee itself, after subtraction.
     */
    public Point subtract(Point point)
    {
        return subtract(point.x(), point.y());
    }

    /**
     * subtracts {@code x} and {@code y} values from {@code pair}.
     * 
     * @param pair {@code Pair} contains x-y pair to subtract.
     * @return callee itself, after subtraction.
     */
    public Point subtract(Pair<? extends Number> pair)
    {
        return subtract(pair.first().doubleValue(), pair.second().doubleValue());
    }

    /**
     * subtracts {@code dx} and {@code dy} to {@code x} and {@code y}.
     * 
     * @param dx value for x to subtract.
     * @param dy value for y to subtract.
     * @return callee itself, after subtraction.
     */
    public Point subtract(double dx, double dy)
    {
        first -= dx;
        second -= dy;

        return this;
    }

    /**
     * multiplies {@code value} to {@code x} and {@code y}.
     * 
     * @param value multiplier.
     * @return callee itself, after multiplication.
     */
    public Point multiply(double value)
    {
        first *= value;
        second *= value;

        return this;
    }

    /**
     * rotates a point {@code rad} from the origin (0, 0).
     * 
     * @param rad angle of rotation, in radian.
     * @return callee itself, after rotation.
     */
    public Point rotate(double rad)
    {
        if (rad != 0)
        {
            double sin = Math.sin(rad), cos = Math.cos(rad), newX = first * cos - second * sin, newY = first * sin + second * cos;

            first = newX;
            second = newY;
        }

        return this;
    }

    /**
     * gets scalar product between two points.
     * 
     * @param point other {@code Point} to product.
     * @return calculated dot product between callee and {@code point}.
     */
    public double dot(Point point)
    {
        return first * point.first + second * point.second;
    }

    /**
     * gets pseudo-cross product between two points. this operation considers each
     * point as a 3D point which of {@code z} is 0.
     * 
     * @param point other {@code Point} to product.
     * @return calculated cross product between callee and {@code point}.
     */
    public double cross(Point point)
    {
        return first * point.second - second * point.first;
    }

    /**
     * gets distance from the origin. (i.e. length of the vector)
     * 
     * @return calculated distance.
     */
    public double length()
    {
        return Math.sqrt(first * first + second * second);
    }

    /**
     * normalizes this point (as positional vector)
     * 
     * @return callee itself, after normalization.
     */
    public Point normalize()
    {
        double len = length();
        Preconditions.checkState(len != 0);

        multiply(1 / len);

        return this;
    }

    /**
     * makes copy of this point.
     * 
     * @return copy of this object.
     */
    public Point clone()
    {
        return new Point(this);
    }
}