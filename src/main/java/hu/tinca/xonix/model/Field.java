package hu.tinca.xonix.model;

import java.awt.*;
import java.awt.geom.*;

import static java.awt.geom.Rectangle2D.*;

/**
 *
 */
public class Field extends AbstractSprite {

    public Field(Dimension d) {
        shape = new FieldShape(d);
    }

    @Override
    public void advance() {
        // empty
    }

    public Shape translateShape() {
        return shape;
    }

    @Override
    public Velocity getVelocity(Point2D point) {
        if (point == null) {
            return new Velocity(Math.toRadians(90), 1);
        }

        switch (((FieldShape) shape).inCode(point)) {
            case OUT_TOP:
            case OUT_BOTTOM:
                return new Velocity(Math.toRadians(90), 1);
            case OUT_LEFT:
            case OUT_RIGHT:
                return new Velocity(Math.toRadians(0), 1);
        }

        throw new IllegalArgumentException(String.format("Outcode of point %s cannot be determined", point));
    }

    public void paint(Graphics2D g) {
        g.setStroke(new BasicStroke(0.01f));
        super.paint(g);
    }

    /////////////////////////////

    private class FieldShape implements Shape {
        private Rectangle2D.Double outer;
        private Rectangle2D.Double inner;
        private GeneralPath path;

        FieldShape(Dimension d) {
            double w = d.getWidth();
            double h = d.getHeight();
            double thickness = 0.004 * w;
            outer = new Rectangle2D.Double(-w / 2, -h / 2, w, h);
            inner = new Rectangle2D.Double(-w / 2 + thickness, -h / 2 + thickness, w - 2 * thickness, h - 2 * thickness);
            path = new GeneralPath(inner);
            path.append(outer, false);
        }

        int inCode(Point2D point) {
            double dx = (distanceFromLeft(point) < distanceFromRight(point)) ? distanceFromLeft(point) : distanceFromRight(point);
            double dy = (distanceFromBottom(point) < distanceFromTop(point)) ? distanceFromBottom(point) : distanceFromTop(point);

            return dx < dy ? OUT_LEFT : OUT_BOTTOM;
        }

        private double distanceFromLeft(Point2D p) {
            return Math.abs(inner.getX() - p.getX());
        }

        private double distanceFromRight(Point2D p) {
            return Math.abs(inner.getX() + inner.getWidth() - p.getX());
        }

        private double distanceFromBottom(Point2D p) {
            return Math.abs(inner.getY() - p.getY());
        }

        private double distanceFromTop(Point2D p) {
            return Math.abs(inner.getY() + inner.getHeight() - p.getY());
        }

        @Override
        public Rectangle getBounds() {
            return outer.getBounds();
        }

        @Override
        public Rectangle2D getBounds2D() {
            return outer.getBounds2D();
        }

        @Override
        public boolean contains(double x, double y) {
            return inner.contains(x, y);
        }

        @Override
        public boolean contains(Point2D p) {
            return inner.contains(p);
        }

        @Override
        public boolean intersects(double x, double y, double w, double h) {
            return intersects(new Rectangle2D.Double(x, y, w, h));
        }

        @Override
        public boolean intersects(Rectangle2D r) {
            return !inner.contains(r) && !outer.contains(r);
        }

        @Override
        public boolean contains(double x, double y, double w, double h) {
            return inner.contains(x, y, w, h);
        }

        @Override
        public boolean contains(Rectangle2D r) {
            return inner.contains(r);
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at) {
            return path.getPathIterator(at);
        }

        @Override
        public PathIterator getPathIterator(AffineTransform at, double flatness) {
            return path.getPathIterator(at, flatness);
        }

    }
}
