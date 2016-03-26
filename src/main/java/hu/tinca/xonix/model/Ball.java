package hu.tinca.xonix.model;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/**
 *
 */
public class Ball extends AbstractSprite {
//    private int counter;

    public Ball(Velocity velocity, double radius) {
        super(new Point2D.Double(0, 0), velocity);
        createShape(radius);
    }

    private void createShape(double radius) {
        shape = new Ellipse2D.Double(0, 0, radius, radius);
    }

//    public void advance() {
//        if (++counter > 200) {
//            counter = 20;
//            Velocity v = getVelocity(null);
//            v.reverse();
//            advancer = new Advancer(getPosition(), v);
//        }
//
//        advancer.advance();
//    }
}
