package hu.tinca.xonix.engine;

import hu.tinca.xonix.model.Xonix;
import hu.tinca.xonix.view.View;

import javax.swing.*;
import java.awt.Graphics2D;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 */
public class Engine {
    private View view;
    private Xonix xonix;
    ScheduledThreadPoolExecutor executor;

    public Engine(View view, Xonix xonix) {
        this.view = view;
        this.xonix = xonix;
        executor = new ScheduledThreadPoolExecutor(1);
        paint();
    }

    void tick() {
        progress();
//        handleCollision();
        paint();
    }

    private void paint() {
        Graphics2D g = (Graphics2D) view.getGraphics();
        xonix.paintSprites(g);
        view.revalidate();
    }

//    private void handleCollision() {
//        xonix.handleCollisions();
//    }

    private void progress() {
        xonix.progress();
    }

    private class Ticker implements Runnable {

        @Override
        public void run() {
            try {
                tick();
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }

    public void start() {
        executor.scheduleAtFixedRate(new Ticker(), 0, 20, TimeUnit.MILLISECONDS);
    }

    public void stop() {
        executor.shutdownNow();
    }

}
