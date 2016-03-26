package hu.tinca.xonix.model;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 */
public class Advancer {
    private static final String NAME = Advancer.class.getName();
    private static final Logger LOG = Logger.getLogger(NAME);

    private Point2D position;
    private final Velocity velocity;
    private AffineTransform transform;

    Advancer(Point2D position, Velocity velocity) {
        this.position = position;
        this.velocity = velocity;
        LOG.entering(NAME, "const", velocity);
        setTransform();
    }

    private void setTransform() {
        transform = AffineTransform.getTranslateInstance(velocity.getX(), velocity.getY());
    }

    public void advance() {
        transform.transform(position, position);
        LOG.exiting(NAME, "advance", position);
    }

    public Point2D.Double getNextPosition() {
        Point2D.Double next = new Point2D.Double();
        transform.transform(position, next);

        return next;
    }

    public void bounce(Velocity v) {
        CollisionFunction.collide(velocity, v);
        LOG.exiting(NAME, "bounce", velocity);
        setTransform();
    }

    public Point2D getPosition() {
        return position;
    }

    public void advanceTo(Point2D position) {
        LOG.entering(NAME, "advanceTo", position);
        this.position = position;
    }

    Velocity getVelocity() {
        return velocity;
    }
}
