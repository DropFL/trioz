package com.dropfl.config;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

public class Config
{
    private List<?> allowed = null;
    private Object defaultValue = null;
    private Object value = null;

    private Config()
    {
    }

    public void setAllowed(@Nonnull final List<?> allowed)
    {
        Preconditions.checkState(this.allowed == null);
        Preconditions.checkState(this.defaultValue == null || allowed.contains(this.defaultValue));

        this.allowed = allowed;
    }

    public void setDefaultValue(@Nonnull final Object value)
    {
        Preconditions.checkState(this.defaultValue == null);
        Preconditions.checkState(this.allowed == null || this.allowed.contains(value));

        this.defaultValue = value;
    }

    public Object getDefaultValue()
    {
        return defaultValue;
    }

    @Override
    public String toString()
    {
        return "{allowed values = " + allowed.toString() + ", " + "default value = " + defaultValue.toString() + ", "
                + "current value = " + value.toString() + "}";
    }

    @SuppressWarnings("unchecked")
    public <T> T as(Class<T> type)
    {
        return (T) value;
    }

    public void set(Object value)
    {
        Preconditions.checkNotNull(this.allowed);
        Preconditions.checkArgument(this.allowed.contains(value));

        this.value = value;
    }

    public Class<?> type()
    {
        return value.getClass();
    }
}