package com.dropfl.platformer.collision;

public abstract class Collider {
    /**
     * determines collision between two bounding boxes
     * @param self {@code BoundingBox} which is owner and caller of this {@code Collider}.
     * @param target {@code BoundingBox} that does not own this {@code Collider}.
     * @return {@code true} if collieded, {@code false} otherwise.
     */
    public abstract boolean isCollided (BoundingBox self, BoundingBox target);
}
