package com.dropfl.config;

import java.awt.RenderingHints;

public class RenderInitializer extends RenderingHintsInitializer
{
    public RenderInitializer(RenderingHints hints)
    {
        super(hints);
    }

    @Override
    protected void init()
    {
        String val = Configurations.get("render").as(String.class);
        Object obj;

        switch (val)
        {
            case "quality":
                obj = RenderingHints.VALUE_RENDER_QUALITY;
                break;
            case "speed":
                obj = RenderingHints.VALUE_RENDER_SPEED;
                break;
            default:    // will not happen
                obj = null;
        }

        hints.put(RenderingHints.KEY_RENDERING, obj);
    }
}