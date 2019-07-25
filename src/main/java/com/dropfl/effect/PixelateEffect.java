package com.dropfl.effect;

import com.dropfl.Main;

import java.awt.*;
import java.awt.image.VolatileImage;

public class PixelateEffect extends ScreenEffect
{
    private int size;

    public PixelateEffect(int size)
    {
        this.size = size;
    }

    public int getSize()
    {
        return size;
    }

    public void setSize(int size)
    {
        this.size = size;
    }

    @Override
    public void apply(VolatileImage image)
    {
        if (size <= 1)
            return;

        updateImage();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);

        graphics.drawImage(image, 0, 0, Main.getWidth() / size, Main.getHeight() / size, null);
        graphics.dispose();

        Graphics2D g = (Graphics2D) image.getGraphics();

        g.drawImage(ScreenEffect.image, 0, 0, Main.getWidth(), Main.getHeight(), 0, 0, Main.getWidth() / size,
                Main.getHeight() / size, null);
        g.dispose();
    }

    @Override
    public void updateProperties(Double[] values)
    {
        size = values[0].intValue();
    }
}
