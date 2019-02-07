package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;

public class SquareToCircleCollider extends Collider {
    @Override
    public boolean isCollided (BoundingBox square, BoundingBox circle) {
        if (square.getRotation() != 0)
            throw new IllegalArgumentException("Square should not be rotated.");
        
        Pair<Double> size   = circle.getSize(),
                     origin = circle.getOrigin();
        
        if (size.x().compareTo(size.y()) != 0)
            throw new IllegalArgumentException("Circle should have square-shaped bounding box.");
        
        double radius  = size.x() / 2,
               rad     = Math.toRadians(circle.getRotation()) + Math.PI / 4,
               centerX = origin.x() + radius * Math.cos(rad) * Math.sqrt(2),
               centerY = origin.y() + radius * Math.sin(rad) * Math.sqrt(2);
        
        double diffX, diffY;
        origin = square.getOrigin();
        size = square.getSize();
        
        if (centerX < origin.x())
            diffX = origin.x() - centerX;
        else if (centerX > origin.x() + size.x())
            diffX = centerX - origin.x() - size.x();
        else
            diffX = 0;

        if (centerY < origin.y())
            diffY = origin.y() - centerY;
        else if (centerY > origin.y() + size.y())
            diffY = centerY - origin.y() - size.y();
        else
            diffY = 0;
        
        return diffY * diffY + diffX * diffX <= radius * radius;
    }
}
