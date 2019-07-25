package com.dropfl.config;

import com.dropfl.key.KeyStatus;

public class KeyStatusInitializer extends Initializer
{
    public KeyStatusInitializer ()
    {
    }

    @Override
    protected void init()
    {
        KeyStatus.init();
    }
}