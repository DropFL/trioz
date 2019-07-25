package com.dropfl.config;

import java.awt.RenderingHints;

public class TextAAInitializer extends RenderingHintsInitializer
{
    public TextAAInitializer(RenderingHints hints)
    {
        super(hints);
    }

    @Override
    protected void init()
    {
        boolean res = Configurations.get("text_antialias").as(Boolean.class);
        Object val = res ? RenderingHints.VALUE_TEXT_ANTIALIAS_ON
                         : RenderingHints.VALUE_TEXT_ANTIALIAS_OFF;

        hints.put(RenderingHints.KEY_TEXT_ANTIALIASING, val);
    }
}