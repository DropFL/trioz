package com.dropfl;

import com.dropfl.config.*;
import com.dropfl.util.Resolution;
import com.dropfl.key.KeyStatus;

import java.awt.*;

public class Main
{
    // global constants goes here
    public static int SCREEN_WIDTH = 1280;
    public static int SCREEN_HEIGHT = 720;
    private static final Resolution RESOLUTION = new Resolution();
    private static RenderingHints RENDERING_HINT = new RenderingHints(null);
    private static Initializer reInit;

    public static void main(String[] args)
    {
        // Hardware Acceleration
        System.setProperty("sun.java2d.opengl", "true");

        // Chain Initializers
        reInit = new ResolutionInitializer(RESOLUTION)
                .append(new TextAAInitializer(RENDERING_HINT))
                .append(new InterpolationInitializer(RENDERING_HINT))
                .append(new AntiAliasInitializer(RENDERING_HINT))
                .append(new RenderInitializer(RENDERING_HINT));
        
        Initializer init = new FontInitializer()
                            .append(new KeyStatusInitializer())
                            .append(reInit);

        Configurations.initalize("config.yml");
        init.start();

        // Create a JFrame
        GameFrame frame = new GameFrame();

        // Register JFrame object
        KeyStatus.register(frame);
    }

    public static int getWidth()
    {
        return RESOLUTION.getWidth();
    }

    public static int getHeight()
    {
        return RESOLUTION.getHeight();
    }

    public static RenderingHints getRenderingHints()
    {
        return RENDERING_HINT;
    }

    public static void reInitialize ()
    {
        reInit.start();
    }
}
