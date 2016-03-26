package hu.tinca.xonix.model;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 *
 */
public class Xonix {
    final public static float SIZE = 10;

    private Lead lead;
    private Chaser chaser;
    private Ball[] balls;
    private Field field;
    private AffineTransform transform;
    private Dimension dimension;

    public Xonix(Dimension d, int numOfBalls) {
        dimension = d;
//        lead = new Lead();
//        chaser = new Chaser();
        balls = createBalls(numOfBalls);
        field = createField();
        transform = createTransform();
    }

    private AffineTransform createTransform() {
        // center point
        double x = dimension.getWidth() / 2;
        double y = dimension.getHeight() / 2;
        return AffineTransform.getTranslateInstance(x, y);
    }

    private Ball[] createBalls(int numOfBalls) {
        Ball[] balls = new Ball[numOfBalls];
        for (int i = 0; i < numOfBalls; i++) {
            balls[i] = createBall();
        }

        return balls;
    }

    private Ball createBall() {
        Ball b = new Ball(createRandomVelocity(), 0.01 * dimension.getHeight());
        b.setColor(Color.BLUE);
        b.setPosition(createRandomPosition());
        return b;
    }

    private Field createField() {
        int path = (int)(0.05 * dimension.getHeight());
        Dimension d = new Dimension((int)dimension.getWidth() - path, (int)dimension.getHeight() - path);
        Field f = new Field(d);
        f.setPosition(new Point2D.Double(0, 0));
        return f;
    }

    public void progress() {
        progressBalls();
//        chaser.advance();
    }

//    public void handleCollisions() {
//        for (Ball b : balls) {
//            b.collide(field);
//        }
//    }

    private void progressBalls() {
        for (Ball b : balls) {
            Point2D p = b.getHitPositionIfAdvanced(field);
            if (p == null) {
                b.advance();
            }
            else {
                b.advanceTo(p);
                b.bounce(field);
            }
        }
    }

    public void paintSprites(Graphics2D g) {
        g.clearRect(0, 0, (int)dimension.getWidth(), (int)dimension.getHeight());
        g.setTransform(transform);
        field.paint(g);
        for (Ball b : balls) {
            b.paint(g);
        }

//        debug(g);
    }

    private void debug(Graphics2D g) {
        g.drawRect(0, 0, 20, 20);
    }

    Point2D.Double createRandomPosition() {
        Random r = new Random();
        double x = 0.8 * dimension.getWidth() * (r.nextDouble() - 0.5);
        double y = 0.8 * dimension.getHeight() * (r.nextDouble() - 0.5);
        return new Point2D.Double(x, y);
    }

    private Velocity createRandomVelocity() {
        Random r = new Random();
        double i = r.nextDouble() - 0.50000056784567;
        double angle = Math.signum(i) * Math.toRadians(45);
//        return new Velocity(angle, 0.002 * dimension.getHeight());
        return new Velocity(Math.toRadians(45), 1.2);
    }
}
