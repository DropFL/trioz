package com.dropfl.config;

public abstract class Initializer
{
    protected Initializer next = null;
    private Initializer last = null;

    public final Initializer append(Initializer obj)
    {
        if (this.last != null)
            this.last.append(obj);
        else
            this.next = obj;

        this.last = obj;

        return this;
    }

    public final void start()
    {
        for (Initializer targ = this; targ != null; targ = targ.next)
        {
            // System.out.println(targ);
            targ.init();
        }
    }

    protected abstract void init();
}