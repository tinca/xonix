package hu.tinca.xonix.model;

/**
 *
 */
public class CollisionFunction {

    private CollisionFunction() { }

    public static void collide(Velocity v1, Velocity v2) {
        if (v2.isAnyZero()) {
            doHalfCollide(v1, v2);
            return;
        }
        else if (v1.isAnyZero()) {
            doHalfCollide(v2, v1);
            return;
        }

        doFullCollide(v1, v2);
    }

    private static void doHalfCollide(Velocity v1, Velocity v2) {
        if (v2.isVerticalOnly()) {
            v1.reverseX();
        }
        else {
            v1.reverseY();
        }
    }

    private static void doFullCollide(Velocity v1, Velocity v2) {
        if (!v1.hasSameXDirection(v2)) {
            if (!v1.hasSameYDirection(v2)) {
                v1.reverse();
                v2.reverse();
            }
            else {
                v1.reverseX();
                v2.reverseX();
            }
        }
        else if (!v1.hasSameYDirection(v2)) {
            v1.reverseY();
            v2.reverseY();
        }
    }


}
