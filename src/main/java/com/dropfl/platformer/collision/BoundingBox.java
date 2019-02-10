package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;
import com.dropfl.util.Point;

public interface BoundingBox {
    /**
     * @return the rotation of this bounding box in degrees.
     */
    double getRotation ();
    // TODO change rotation from degree to radian!

    /**
     * @return origin point (i.e. the pivot of rotation, most upper-left point when rotation is zero) in pair.
     */
    Point getOrigin ();
    
    /**
     * @return width-height pair, ignoring rotation.
     */
    Pair<Double> getSize ();
}
