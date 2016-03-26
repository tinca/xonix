package hu.tinca.xonix.model;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 */
public class VelocityTest {

    @Test
    public void horizontalVelocityXComponent() {
        Velocity v = new Velocity(0, 1);

        Assert.assertEquals(v.getX(), 1.0);
    }

    @Test
    public void horizontalVelocityYComponent() {
        Velocity v = new Velocity(0, 1);

        Assert.assertEquals(v.getY(), 0.0);
    }

    @Test
    public void verticalVelocityXComponent() {
        Velocity v = new Velocity(Math.PI/2, 1);

        Assert.assertEquals(v.getX(), 0.0);
    }

    @Test
    public void verticalVelocityYComponent() {
        Velocity v = new Velocity(Math.PI/2, 1);

        Assert.assertEquals(v.getY(), 1.0);
    }
}
