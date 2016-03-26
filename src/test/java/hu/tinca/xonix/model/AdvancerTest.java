package hu.tinca.xonix.model;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import static java.awt.geom.Rectangle2D.OUT_LEFT;
import static java.awt.geom.Rectangle2D.OUT_TOP;

/**
 *
 */
public class AdvancerTest {
    private static double THRESHOLD = 1E-3;
    private Advancer advancer;
    private Shape shape;

    @BeforeMethod
    public void setUp() {
        advancer = new Advancer(new Point2D.Double(0, 0), new Velocity());
    }

    @Test
    public void advance() {
        advancer.advance();

        Point2D p = advancer.getPosition();
        Assert.assertEquals(p.getX(), 0.7071, THRESHOLD);
        Assert.assertEquals(p.getY(), 0.7071, THRESHOLD);
    }

    @Test
    public void bounceOnHorizontal() {
        advancer.advance();
        advancer.bounce(new Velocity(Math.PI, 0));
        advancer.advance();

        Point2D p = advancer.getPosition();
        Assert.assertEquals(p.getX(), 1.4142, THRESHOLD);
        Assert.assertEquals(p.getY(), 0, THRESHOLD);
        Velocity v = advancer.getVelocity();
        Assert.assertEquals(v.getAngle(), -Math.PI/4);
    }

    @Test
    public void bounceOnVertical() {
        advancer.advance();

        advancer.bounce(new Velocity(Math.PI/2, 0));
        advancer.advance();

        Point2D p = advancer.getPosition();
        Assert.assertEquals(p.getX(), 0, THRESHOLD);
        Assert.assertEquals(p.getY(), 1.4142, THRESHOLD);
        Velocity v = advancer.getVelocity();
        Assert.assertEquals(v.getAngle(), 3 * Math.PI / 4);
    }

    @Test
    public void outCodeExplore() {
        Rectangle2D r = new Rectangle2D.Double(-10, -10, 20, 20); // center: 0, 0

        Assert.assertEquals(r.outcode(-11, 5), OUT_LEFT);
        Assert.assertEquals(r.outcode(-11, -11), OUT_LEFT | OUT_TOP);   // top left corner
        Assert.assertEquals(r.outcode(5, 5), 0);  // inside, no code
    }
}
