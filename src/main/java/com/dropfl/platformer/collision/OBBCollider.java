package com.dropfl.platformer.collision;

import java.util.ArrayList;

import com.dropfl.util.Pair;

public class OBBCollider extends Collider {
    
    @Override
    public boolean isCollided (BoundingBox box1, BoundingBox box2) {
        double rad1 = Math.toRadians(box1.getRotation()),
               rad2 = Math.toRadians(box2.getRotation());
        Pair<Double> origin, size, range_x, range_y;
        Pair<Pair<Double>> range;
        
        // test box2 relative to box1
        origin = rotate(box1.getOrigin(), -rad1);
        size = box1.getSize();
        range = projRange(box2, rad1);
        range_x = range.x();
        range_y = range.y();

        if ( origin.x()            > range_x.second() ||
             origin.x() + size.x() < range_x.first()  ||
             origin.y()            > range_y.second() ||
             origin.y() + size.y() < range_y.first()     ) return false;
        
        // test box1 relative to box2
        origin = rotate(box2.getOrigin(), -rad2);
        size = box2.getSize();
        range = projRange(box1, rad2);
        range_x = range.x();
        range_y = range.y();

        if ( origin.x() > range_x.second()           ||
             origin.x() + size.x() < range_x.first() ||
             origin.y() > range_y.second()           ||
             origin.y() + size.y() < range_y.first()    ) return false;

        return true;
    }

    /**
     * 
     * @param box target {@code BoundngBox} to apply projection.
     * @param rad radians of the line passes the origin point (0, 0). {@code box} will be projected upon this line and its normal line (which also passes the origin).
     * @return ranges of projection onto each line; first is the original, second is its normal.
     */
    private Pair<Pair<Double>> projRange (BoundingBox box, double rad) {
        double boxRad = Math.toRadians(box.getRotation());

        Pair<Double> size = box.getSize(), origin = box.getOrigin(),
                     w = rotate(new Pair<Double>(size.x(), 0.), boxRad),
                     h = rotate(new Pair<Double>(0., size.y()), boxRad);
        ArrayList<Pair<Double>> points = new ArrayList<>();

        points.add(origin);
        points.add(new Pair<Double>(origin.x() + w.x()        , origin.y() + w.y())        );
        points.add(new Pair<Double>(origin.x()         + h.x(), origin.y()         + h.y()));
        points.add(new Pair<Double>(origin.x() + w.x() + h.x(), origin.y() + w.y() + h.y()));

        for (Pair<Double> point : points) rotate(point, -rad);

        Pair<Double> result_x = new Pair<>(points.get(0).x(), points.get(0).x()),
                     result_y = new Pair<>(points.get(0).y(), points.get(0).y());

        for (Pair<Double> point : points) {
            if (result_x.first()  > point.x()) result_x.first(point.x());
            if (result_x.second() < point.x()) result_x.second(point.x());
            if (result_y.first()  > point.y()) result_y.first(point.y());
            if (result_y.second() < point.y()) result_y.second(point.y());
        }

        return new Pair<Pair<Double>>(result_x, result_y);
    }

    /**
     * rotates a vector in given radian.
     * @param p target vector represented in {@code Pair<Double>}.
     * @param rad radians to rotate {@code p}.
     * @return rotated vector, which is same to {@code p}.
     */
    private Pair<Double> rotate (Pair<Double> p, double rad) {
        double sin = Math.sin(rad), cos = Math.cos(rad),
               newX = p.x() * cos - p.y() * sin,
               newY = p.x() * sin + p.y() * cos;
        
        p.x(newX);
        p.y(newY);

        return p;
    }
}