package com.dropfl.config;

import java.awt.RenderingHints;

import com.dropfl.util.Resolution;

import org.junit.Test;

/**
 * Unit test for configurations.
 */
public class ConfigTest
{
    @Test
    public void configLoadTest() throws Exception
    {
        Configurations.initalize("config.yml");
        System.out.println(Configurations.getProperties());
    }

    @Test
    public void initializeTest() throws Exception
    {
        RenderingHints hints = new RenderingHints(null);
        Resolution res = new Resolution();
        Initializer init =
            new ResolutionInitializer(res)
                .append(new TextAAInitializer(hints))
                .append(new InterpolationInitializer(hints))
                .append(new AntiAliasInitializer(hints))
                .append(new RenderInitializer(hints));

        Configurations.initalize("config.yml");
        init.start();

        System.out.println(res);
        System.out.println(hints);
    }
}
