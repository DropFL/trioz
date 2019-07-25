package com.dropfl.config;

import java.awt.RenderingHints;

public class InterpolationInitializer extends RenderingHintsInitializer
{
    public InterpolationInitializer(RenderingHints hints)
    {
        super(hints);
    }

    @Override
    protected void init()
    {
        String val = Configurations.get("interpolation").as(String.class);
        Object obj;

        switch (val)
        {
            case "bilinear":
                obj = RenderingHints.VALUE_INTERPOLATION_BILINEAR;
                break;
            case "bicubic":
                obj = RenderingHints.VALUE_INTERPOLATION_BICUBIC;
                break;
            case "nearest-neighbor":
                obj = RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR;
                break;
            default:    // will not happen
                obj = null;
        }

        hints.put(RenderingHints.KEY_INTERPOLATION, obj);
    }
}