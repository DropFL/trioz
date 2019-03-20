package com.dropfl.platformer.collision;

/**
 * {@code CollisionType} is a collection of collision detector wrappers.
 * Each element of this enumerator has a implementation of unique algorithm.
 * <p>
 * Since detectors only have {@code BoundingBox}es to determine the original shapes and collisions between them,
 * those algorithms are about simple shapes (square, circle, etc.); cannot use to check complex ones.
 * 
 * @see #AABB
 * @see #OBB
 * @see #CIRC_2_SQ
 */
public enum CollisionType {

    /**
     * <strong>AABB</strong> is an abbrevation of
     * <strong>A</strong>xis
     * <strong>A</strong>ligned
     * <strong>B</strong>ounding
     * <strong>B</strong>ox.
     * This type detects collision between two unrotated {@code BoundingBox}es.
     * <p>
     * If you call {@link #isCollided()} where any {@code BoundingBox} is rotated
     * (i.e. {@link BoundingBox#rotation()} returns non-zero value),
     * the function will throw {@code IllegalArgumentException}.
     * 
     * @see #OBB
     */
    AABB(new AABBDetector()),

    /**
     * <strong>OBB</strong> is an abbrevation of
     * <strong>O</strong>riented
     * <strong>B</strong>ounding
     * <strong>B</strong>ox.
     * This type detects collision between two rotated {@code BoundingBox}es.
     * <p>
     * Calling {@link #isCollided()} with unrotated ones
     * (i.e. {@link BoundingBox#rotation()} returns 0),
     * does not matter at all, but the execution speed is slower than {@link #AABB}.
     * 
     * @see #AABB
     */
    OBB(new OBBDetector()),

    /**
     * {@code CIRC_2_SQ} means circle-to-square.
     * This type detects collision between circle and square (or rectangle).
     * <p>
     * <strong>Note</strong>: this type does NOT treat two {@code BoundingBox}es the same;
     * one as a circle, other one as a rectangle.
     * So please keep the order of arguments when calling {@link #isCollided()};
     * it may throw {@code IllegalArgumentException}.
     * <p>
     * <strong>Note2</strong>: it CANNOT detect collision between ellipse (not circle) and rectangle.
     * So a {@code BoundingBox} corresponds to circle MUST be square-shaped, not rectangle.
     * If not so, {@link #isCollided()} will throw {@code IllegalArgumentException}.
     */
    CIRC_2_SQ(new CircleToSquareDetector());

    private final IDetector detector;

    CollisionType (IDetector detector) { this.detector = detector; }

    /**
     * Determines collision between two {@code BoundingBox}es.
     * This function checks only whether they are collided; it cannot detect more information like direction.
     * <p>
     * <strong>Note:</strong> each type may treat given {@code BoundingBox}es in different forms.
     * Aware that some types can produce different results with the same arguments.
     * 
     * @param self {@code BoundingBox} who references this {@code CollisionType}.
     * @param target other {@code BoundingBox} to perform hit-test with {@code self}.
     * @return {@code true} if given objects are collided, {@code false} otherwise.
     * 
     * @see BoundingBox
     */
    public boolean isCollided (BoundingBox self, BoundingBox target) {
        return detector.detect(self, target);
    }
}