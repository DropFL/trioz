package com.dropfl.platformer.collision;

import java.util.ArrayList;

import com.dropfl.util.Pair;
import com.dropfl.util.Point;

class OBBDetector implements IDetector
{
    @Override
    public boolean detect(BoundingBox box1, BoundingBox box2)
    {
        double rad1 = box1.rotation(), rad2 = box2.rotation();

        Point origin, end;
        Pair<Double> range_x, range_y;
        Pair<Pair<Double>> range;

        // test box2 relative to box1
        origin = box1.upperLeft().rotate(-rad1);
        end = origin.clone().add(box1.width(), box1.height());
        range = projRange(box2, rad1);
        range_x = range.first();
        range_y = range.second();

        if (origin.x() > range_x.second() || end.x() < range_x.first() ||
            origin.y() > range_y.second() || end.y() < range_y.first()  )   return false;

        // test box1 relative to box2
        origin = box2.upperLeft().rotate(-rad2);
        end = origin.clone().add(box2.width(), box2.height());
        range = projRange(box1, rad2);
        range_x = range.first();
        range_y = range.second();

        if (origin.x() > range_x.second() || end.x() < range_x.first() ||
            origin.y() > range_y.second() || end.y() < range_y.first()  )   return false;

        return true;
    }

    /**
     * calculates range of projection on given line and its normal.
     * 
     * @param box target {@code BoundngBox} to apply projection.
     * @param rad radians of the line passes the origin point (0, 0). (normal also
     *            passes the origin.)
     * @return ranges of projection on each line; first is the original, second is
     *         its normal.
     */
    private Pair<Pair<Double>> projRange(BoundingBox box, double rad)
    {
        Point origin = box.upperLeft(),
              w = (new Point(box.width(),  0)).rotate(box.rotation()),
              h = (new Point(0, box.height())).rotate(box.rotation());

        ArrayList<Point> points = new ArrayList<>();

        points.add(origin.clone().add(w));
        points.add(origin.clone().add(h));
        points.add(origin.clone().add(w).add(h));

        origin.rotate(-rad);
        for (Point point : points)
            point.rotate(-rad);

        Pair<Double> result_x = new Pair<>(origin.x(), origin.x()),
                     result_y = new Pair<>(origin.y(), origin.y());

        for (Point point : points)
        {
            if (result_x.first() > point.x())
                result_x.first(point.x());
            else if (result_x.second() < point.x())
                result_x.second(point.x());

            if (result_y.first() > point.y())
                result_y.first(point.y());
            else if (result_y.second() < point.y())
                result_y.second(point.y());
        }

        return new Pair<Pair<Double>>(result_x, result_y);
    };
}