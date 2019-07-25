package com.dropfl.util;

public final class Resolution extends Pair<Integer>
{
    @SuppressWarnings("unused")
    private int width;
    @SuppressWarnings("unused")
    private int height;

    public int getWidth()
    {
        return first;
    }

    public void setWidth(final int width)
    {
        this.first = width;
    }

    public int getHeight()
    {
        return second;
    }

    public void setHeight(final int height)
    {
        this.second = height;
    }

    @Override
    public String toString()
    {
        return first + "x" + second;
    }
}