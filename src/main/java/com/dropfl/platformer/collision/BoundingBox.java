package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;

public interface BoundingBox {
	/**
	 * @return the rotation of this bounding box in degrees.
	 */
	double getRotation ();

	/**
	 * @return origin point (i.e. the pivot of rotation, most upper-left point when rotation is zero) in pair.
	 */
	Pair<Double> getOrigin ();
	
	/**
	 * @return width-height pair, ignoring rotation.
	 */
	Pair<Double> getSize ();
}
