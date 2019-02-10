package com.dropfl.platformer.collision;

import com.dropfl.util.Point;

public class AABBCollider extends Collider {

    @Override
    public boolean isCollided (BoundingBox box1, BoundingBox box2) {

        if (box1.getRotation() != 0 || box2.getRotation() != 0)
            throw new IllegalArgumentException("AABB Collider cannot handle rotated BoundingBox.");
        
        Point p1 = box1.getOrigin(),
              q1 = box2.getOrigin(),
              p2 = p1.clone().add(box1.getSize()),
              q2 = q1.clone().add(box2.getSize());
        
        double  pivotLeft   = (p1.x() > q1.x()) ? p1.x() : q1.x(),
                pivotRight  = (p2.x() < q2.x()) ? p2.x() : q2.x(),
                pivotTop    = (p1.y() > q1.y()) ? p1.y() : q1.y(),
                pivotBottom = (p2.y() < q2.y()) ? p2.y() : q2.y();
        
        return pivotLeft < pivotRight && pivotTop < pivotBottom;
    }
}
