package com.dropfl.config;

import java.awt.RenderingHints;

public class AntiAliasInitializer extends RenderingHintsInitializer
{
    public AntiAliasInitializer(RenderingHints hints)
    {
        super(hints);
    }

    @Override
    protected void init()
    {
        boolean res = Configurations.get("antialias").as(Boolean.class);
        Object val = res ? RenderingHints.VALUE_ANTIALIAS_ON
                         : RenderingHints.VALUE_ANTIALIAS_OFF;

        hints.put(RenderingHints.KEY_ANTIALIASING, val);
    }
}