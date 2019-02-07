package com.dropfl.platformer.collision;

public abstract class Collider {
	/**
	 * determines collision between two bounding boxes
	 * @param s1 bounding box object
	 * @param s2 bounding box object
	 * @return {@code true} if collieded, {@code false} otherwise.
	 */
	public abstract boolean isCollided (BoundingBox s1, BoundingBox s2);
}
