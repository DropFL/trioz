package com.dropfl.platformer.entity;

import com.dropfl.component.ImageComponent;
import com.dropfl.platformer.collision.BoundingBox;
import com.dropfl.util.Pair;
import com.dropfl.util.Point;

/**
 * <p><i>This class is incomplete; following description is the goal.</i></p>
 * 
 * {@code ImprovedEntity} is an improved version of {@code Entity} that can be
 * instantiated by {@code EntityFactory}. The factory gathers resources based on
 * entity-description file (refer {@code doc/entity.ts}) and generates an instance.
 */
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
