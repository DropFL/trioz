package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;
import com.dropfl.util.Point;

public interface AxisAlignedBoundingBox extends BoundingBox {
    default double getRotation() {
        return 0;
    }

    default Point getBottomRight () {
        return getOrigin().add(getSize());
    }

    default Point getCenter () {
        Pair<Double> size = getSize();
        return getOrigin().add(size.first() / 2, size.second() / 2);
    }
}