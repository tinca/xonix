package hu.tinca.xonix.model;

/**
 *
 */
public class Velocity {
    private static final double THRESHOLD = 1E-5;
    private double angle;
    private double rate;
    private double x;
    private double y;

    public Velocity() {
        this(Math.PI/4, 1);
    }

    public Velocity(double angle, double rate) {
        this.angle = angle;
        this.rate = rate;
        calcX();
        calcY();
    }

    public double getRate() {
        return rate;
    }


    private int getSign() {
        return angle >= 0 ? 1 : -1;
    }

    public void reverseX() {
        x*= -1;
        calcAngle();
    }

    public void reverseY() {
        y*= -1;
        calcAngle();
    }

    public void reverse() {
        x*= -1;
        y*= -1;
        calcAngle();
    }

    public double getAngle() {
        return angle;
    }

    private void calcAngle() {
        angle = Math.atan2(y,x);
    }

    public double cosAngle() {
        return Math.cos(angle);
    }

    public double sinAngle() {
        return Math.sin(angle);
    }

    public double tanAngle() {
        return Math.tan(angle);
    }

    public boolean isAnyZero() {
        return isVerticalOnly() || isHorizontalOnly();
    }

    private void calcX() {
        double x = cosAngle() * rate;
        this.x = Math.abs(x) < THRESHOLD ? 0 : x;
    }

    public double getX() {
        return x;
    }

    private void calcY() {
        double y = sinAngle() * rate;
        this.y = Math.abs(y) < THRESHOLD ? 0 : y;
    }

    public double getY() {
        return y;
    }

    public boolean isVerticalOnly() {
        return Math.abs(cosAngle()) < THRESHOLD;
    }

    public boolean isHorizontalOnly() {
        return Math.abs(sinAngle()) < THRESHOLD;
    }

    public boolean hasSameXDirection(Velocity v) {
        return Math.signum(x) == Math.signum(v.x);
    }

    public boolean hasSameYDirection(Velocity v) {
        return Math.signum(y) == Math.signum(v.y);
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "angle=" + angle +
                ", rate=" + rate +
                '}';
    }
}
