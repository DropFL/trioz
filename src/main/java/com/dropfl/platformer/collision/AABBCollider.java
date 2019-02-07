package com.dropfl.platformer.collision;

import com.dropfl.util.Pair;

public class AABBCollider extends Collider {

	@Override
	public boolean isCollided (BoundingBox box1, BoundingBox box2) {

		if (box1.getRotation() != 0 || box2.getRotation() != 0)
			throw new IllegalArgumentException("AABB Collider cannot handle rotated BoundingBox.");
		
		Pair<Double> p1 = box1.getOrigin(), q1 = box2.getOrigin(),
					 s1 = box1.getSize(), s2 = box2.getSize(),
					 p2 = new Pair<Double>(p1.x() + s1.x(), p1.y() + s1.y()),
					 q2 = new Pair<Double>(q1.x() + s2.x(), q1.y() + s2.y());
		
		double  pivotLeft   = (p1.x() > q1.x()) ? p1.x() : q1.x(),
				pivotRight  = (p2.x() < q2.x()) ? p2.x() : q2.x(),
				pivotTop    = (p1.y() > q1.y()) ? p1.y() : q1.y(),
				pivotBottom = (p2.y() < q2.y()) ? p2.y() : q2.y();
		
		return pivotLeft < pivotRight && pivotTop < pivotBottom;
	}
}
