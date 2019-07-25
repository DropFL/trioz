package com.dropfl.config;

import java.awt.RenderingHints;

public abstract class RenderingHintsInitializer extends Initializer
{
    protected RenderingHints hints;

    protected RenderingHintsInitializer(RenderingHints hints)
    {
        this.hints = hints;
    }
}
