package com.dropfl.platformer.entity;

import com.dropfl.component.ImageComponent;
import com.dropfl.platformer.collision.BoundingBox;
import com.dropfl.util.Pair;
import com.dropfl.util.Point;

public class ImprovedEntity extends ImageComponent implements BoundingBox {
    
    // private boolean isEnemy;
    private double width;
    private double height;
    
    @Override
    public double getRotation () {
        return rotation;
    }

    @Override
    public Point getOrigin () {
        return new Point(x, y);
    }

    @Override
    public Pair<Double> getSize () {
        return new Pair<Double>(width, height);
    }

    public void setX (double x) {
        this.x = x;
    }

    public void setY (double y) {
        this.y = y;
    }
    
    public void setRotation (double rotation) {
        this.rotation = rotation;
    }
}
