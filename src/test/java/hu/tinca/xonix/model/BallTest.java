package hu.tinca.xonix.model;

import mockit.Mock;
import mockit.MockUp;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.geom.Point2D;

/**
 *
 */
public class BallTest {

    @Test
    public void bouncing() {
        Ball ball = new Ball(new Velocity(Math.toRadians(45), 2), 1);
        Sprite against = new MockUp<Sprite>() {
            @Mock
            Point2D getPosition() {
                return new Point2D.Double(2.5, 2.5);
            }
        }.getMockInstance();

        Point2D hit = ball.getHitPositionIfAdvanced(against);

        Assert.assertTrue(hit != null);
    }

}
