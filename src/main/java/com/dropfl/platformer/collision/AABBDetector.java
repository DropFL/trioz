package com.dropfl.platformer.collision;

import com.dropfl.util.Point;
import com.google.common.base.Preconditions;

class AABBDetector implements IDetector
{
    @Override
    public boolean detect(BoundingBox box1, BoundingBox box2)
    {
        Preconditions.checkArgument(box1.rotation() == 0 && box2.rotation() == 0);

        Point p1 = box1.upperLeft(),  q1 = box2.upperLeft(),
              p2 = box1.rightBelow(), q2 = box2.rightBelow();

        double pivotLeft   = (p1.x() > q1.x()) ? p1.x() : q1.x(),
               pivotRight  = (p2.x() < q2.x()) ? p2.x() : q2.x(),
               pivotTop    = (p1.y() > q1.y()) ? p1.y() : q1.y(),
               pivotBottom = (p2.y() < q2.y()) ? p2.y() : q2.y();

        return pivotLeft < pivotRight && pivotTop < pivotBottom;
    }
}