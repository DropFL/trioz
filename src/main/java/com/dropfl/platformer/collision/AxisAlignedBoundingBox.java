package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;

public interface AxisAlignedBoundingBox extends BoundingBox {
	default double getRotation() {
		return 0;
	}

	default Pair<Double> getBottomRight () {
		Pair<Double> origin = getOrigin();
		Pair<Double> size = getSize();

		return new Pair<Double>(origin.x() + size.x(), origin.y() + size.y());
	}

	default Pair<Double> getCenter () {
		Pair<Double> origin = getOrigin();
		Pair<Double> size = getSize();

		return new Pair<Double>(origin.x() + size.x() / 2, origin.y() + size.y() / 2);
	}
}