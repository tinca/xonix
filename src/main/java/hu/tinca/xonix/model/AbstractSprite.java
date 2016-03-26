package hu.tinca.xonix.model;

import hu.tinca.xonix.util.ShapeUtil;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.List;

/**
 *
 */
abstract public class AbstractSprite implements Sprite {
    protected Advancer advancer;
    protected Shape shape;
    private Color color = Color.BLACK;

    public AbstractSprite() {
        this(new Point2D.Double(0, 0), new Velocity());
    }

    public AbstractSprite(Point2D.Double position, Velocity velocity) {
        advancer = new Advancer(position, velocity);
    }

    /**
     * Changes position as determined by velocity.
     */
    public void advance() {
        advancer.advance();
    }

    public void advanceTo(Point2D p) {
        advancer.advanceTo(p);
    }

    public boolean bounce(Sprite sprite) {
        advancer.bounce(getVelocity(sprite.getPosition()));
        return false;
    }

    public Velocity getVelocity(Point2D point) {
        return advancer.getVelocity();
    }

    protected Point2D.Double calculatePartialAdvance(List<Point2D> hitPoints) {
        double dx = calcDx(hitPoints);
        double x = getPosition().getX() - dx;
        double dy = calcDy(dx);
        double y = getPosition().getY() - dy;
        return new Point2D.Double(x, y);
    }

    private double calcDy(double dx) {
        return advancer.getVelocity().tanAngle() * dx;
    }

    private double calcDx(List<Point2D> hitPoints) {
        Point2D shapeCentre = getPosition();
        double xIntersection = hitPoints.get(0).getX();
        double halfWidth = shape.getBounds2D().getWidth() / 2;
        return halfWidth - Math.abs(shapeCentre.getX() - xIntersection);
    }

    public Point2D getPosition() {
        return advancer.getPosition();
    }

    public Point2D getNextPosition() {
        return advancer.getNextPosition();
    }


    public void setPosition(Point2D.Double position) {
        advancer.advanceTo(position);
    }

    @Override
    public Point2D getHitPositionIfAdvanced(Sprite sprite) {
        List<Point2D> hitPoints = ShapeUtil.intersection(translateShape(), sprite.translateShape());
        return hitPoints.isEmpty() ? getNextPosition() : calculatePartialAdvance(hitPoints);
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void paint(Graphics2D g2) {
        g2.setColor(color);
        g2.translate(getPosition().getX(), getPosition().getY());
        g2.draw(shape);
    }

    public Shape translateShape() {
        AffineTransform tr = AffineTransform.getTranslateInstance(getPosition().getX(), getPosition().getY());
        return tr.createTransformedShape(shape);
    }


}
