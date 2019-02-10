package com.dropfl.component;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class ImageComponent implements IDrawable {
    
    protected double x;
    protected double y;
    // TODO replace x, y to point object
    protected double rotation;
    protected Image image;
    
    @Override
    public void render (Graphics2D g) {
        AffineTransform t = new AffineTransform();

        t.translate(x, y);
        t.rotate(rotation);

        g.drawImage(image, t, null);
    }
}
