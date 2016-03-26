package hu.tinca.xonix.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class CollisionFunctionTest {

    @Test
    public void collideWithHorizontalIn1stQuadrant() {
        Velocity v1 = new Velocity(Math.toRadians(45), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(90), 1);

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), x1);
        Assert.assertEquals(v1.getY(), -y1);
    }

    @Test
    public void collideWithHorizontalIn2ndQuadrant() {
        Velocity v1 = new Velocity(Math.toRadians(135), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(90), 1);

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), x1);
        Assert.assertEquals(v1.getY(), -y1);
    }

    @Test
    public void collideWithHorizontalIn3rdQuadrant() {
        Velocity v1 = new Velocity(Math.toRadians(-135), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(90), 1);

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), x1);
        Assert.assertEquals(v1.getY(), -y1);
    }

    @Test
    public void collideWithHorizontalIn4thQuadrant() {
        Velocity v1 = new Velocity(Math.toRadians(-45), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(90), 1);

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), x1);
        Assert.assertEquals(v1.getY(), -y1);
    }

    @Test
    public void collideWithVerticalIn1stQuadrant() {
        Velocity v1 = new Velocity(Math.toRadians(45), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(0), 1);

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), -x1);
        Assert.assertEquals(v1.getY(), y1);
    }

    @Test
    public void mutualCollideInRightHalf() {
        Velocity v1 = new Velocity(Math.toRadians(-45), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(45), 1);
        double x2 = v2.getX();
        double y2 = v2.getY();

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), x1);
        Assert.assertEquals(v1.getY(), -y1);
        Assert.assertEquals(v2.getX(), x2);
        Assert.assertEquals(v2.getY(), -y2);
    }

    @Test
    public void mutualInFrontCollide() {
        Velocity v1 = new Velocity(Math.toRadians(-45), 1);
        double x1 = v1.getX();
        double y1 = v1.getY();
        Velocity v2 = new Velocity(Math.toRadians(135), 1);
        double x2 = v2.getX();
        double y2 = v2.getY();

        CollisionFunction.collide(v1, v2);

        Assert.assertEquals(v1.getX(), -x1);
        Assert.assertEquals(v1.getY(), -y1);
        Assert.assertEquals(v2.getX(), -x2);
        Assert.assertEquals(v2.getY(), -y2);
    }
}
