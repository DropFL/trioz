package com.dropfl.platformer.entity;

import com.dropfl.component.ImageComponent;
import com.dropfl.platformer.collision.BoundingBox;

public abstract class Entity extends ImageComponent implements BoundingBox {
    
    protected double width;
    protected double height;
    
    @Override
    public double rotation () {
        return rotation;
    }

    @Override
    public double x () {
        return x;
    }

    @Override
    public double y () {
        return y;
    }

    @Override
    public double width () {
        return width;
    }

    @Override
    public double height () {
        return height;
    }

    public void x (double x) {
        this.x = x;
    }

    public void y (double y) {
        this.y = y;
    }
    
    public void rotation (double rotation) {
        this.rotation = rotation;
    }
}
