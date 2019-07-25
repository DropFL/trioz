package com.dropfl.config;

import res.FontResource;

public class FontInitializer extends Initializer
{
    public FontInitializer ()
    {
    }

    @Override
    protected void init()
    {
        FontResource.registerFonts();
    }
}