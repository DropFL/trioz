package com.dropfl.platformer.collision;

import com.dropfl.util.Point;

public class SquareToCircleCollider extends Collider {
    @Override
    public boolean isCollided (BoundingBox circle, BoundingBox square) {
        if (square.rotation() != 0)
            throw new IllegalArgumentException("Square should not be rotated.");

        double radius;
        
        if ((radius = circle.width()) != circle.height())
            throw new IllegalArgumentException("Circle should have square-shaped bounding box.");
        
        radius /= 2;
        
        Point center = circle.center();
        
        Point diff = new Point(),
              origin = square.upperLeft(),
              end = square.rightBelow();
        
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
