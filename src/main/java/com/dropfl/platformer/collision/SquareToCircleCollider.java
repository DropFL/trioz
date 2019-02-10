package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;
import com.dropfl.util.Point;

public class SquareToCircleCollider extends Collider {
    @Override
    public boolean isCollided (BoundingBox square, BoundingBox circle) {
        if (square.getRotation() != 0)
            throw new IllegalArgumentException("Square should not be rotated.");
        
        Pair<Double> size = circle.getSize();
        
        if (size.first().compareTo(size.second()) != 0)
            throw new IllegalArgumentException("Circle should have square-shaped bounding box.");
        
        
        double radius = size.first() / 2;
        
        Point center = circle.getOrigin().add( (new Point(radius, radius)).rotate(circle.getRotation()) );
        
        Point diff = new Point(),
              origin = square.getOrigin(),
              end = origin.clone().add(square.getSize());
        
        if (center.x() < origin.x())
            diff.x(origin.x() - center.x());
        else if (center.x() > end.x())
            diff.x(center.x() - end.x());

        if (center.y() < origin.y())
            diff.y(origin.y() - center.y());
        else if (center.y() > end.y())
            diff.y(center.y() - end.y());
        
        return diff.length() <= radius;
    }
}
