package com.dropfl.platformer.collision;

import com.dropfl.util.Point;
import com.google.common.base.Preconditions;

class CircleToSquareDetector implements IDetector
{
    @Override
    public boolean detect(BoundingBox circle, BoundingBox square)
    {
        Preconditions.checkArgument(square.rotation() == 0);

        double radius = circle.width();

        Preconditions.checkArgument(radius == circle.height());

        radius /= 2;

        Point center = circle.center();
        Point diff = new Point(), origin = square.upperLeft(), end = square.rightBelow();

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