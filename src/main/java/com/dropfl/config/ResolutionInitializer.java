package com.dropfl.config;

import com.dropfl.util.Resolution;

public class ResolutionInitializer extends Initializer
{
    private Resolution res;

    public ResolutionInitializer (Resolution res)
    {
        this.res = res;
    }

    @Override
    protected void init()
    {
        Resolution res = Configurations.get("resolution").as(Resolution.class);

        this.res.setWidth(res.getWidth());
        this.res.setHeight(res.getHeight());
    }
}