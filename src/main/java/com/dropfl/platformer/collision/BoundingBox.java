package com.dropfl.platformer.collision;

import com.dropfl.util.Point;

/**
 * {@code BoundingBox} defines some methods to describe a imaginary rectangular hitarea.
 * You can draw the bounding box of an object in this way:
 * 
 * <ol>
 *   <li>draw a rectangle with {@code width}x{@code height} size.</li>
 *   <li>place its upper-left point at ({@code x}, {@code y}).</li>
 *   <li>with the point fixed, turn it by {@code rotation} in CCW.</li>
 * </ol>
 * 
 * Note that pivot of rotation in 3rd step is <strong>NOT</strong> the global origin point (0, 0).
 * 
 * @see CollisionType
 */
public interface BoundingBox {
    /**
     * @return rotation of the rectangle in radians.
     */
    double rotation ();

    /**
     * @return horizontal offset of the hitarea.
     */
    double x ();

    /**
     * @return vertical offset of the hitarea.
     */
    double y ();

    /**
     * @return width of the hitarea.
     */
    double width ();

    /**
     * @return height of the hitarea.
     */
    double height ();

    /**
     * @return location of the original upper-left vertex as a point.
     */
    default Point upperLeft () {
        return new Point(x(), y());
    }

    /**
     * @return location of the original right-below vertex as a point.
     */
    default Point rightBelow () {
        return new Point(width(), height())
                    .rotate(rotation())
                    .add(x(), y());
    }

    /**
     * @return location of the center of hitarea as a point.
     */
    default Point center () {
        return new Point(width(), height())
                    .multiply(0.5)
                    .rotate(rotation())
                    .add(x(), y());
    }
}
