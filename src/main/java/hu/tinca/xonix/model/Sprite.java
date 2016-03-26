package hu.tinca.xonix.model;

import java.awt.Shape;
import java.awt.geom.Point2D;

/**
 *
 */
public interface Sprite {

    /**
     * Gets the velocity of this sprite. It may depend on position.
     * @param point
     * @return
     */
    Velocity getVelocity(Point2D point);

    Point2D getPosition();

    Shape translateShape();

    void advance();

    void advanceTo(Point2D p);

    boolean bounce(Sprite sprite);

    /**
     * Checks whether with both sprite advanced what the hit position of this sprite would be.
     */
    Point2D getHitPositionIfAdvanced(Sprite sprite);

}
