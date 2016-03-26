package hu.tinca.xonix;

import hu.tinca.xonix.engine.Engine;
import hu.tinca.xonix.model.Xonix;
import hu.tinca.xonix.view.View;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;

/**
 *
 */
public class XonixGame {

    public static void main(String[] a) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame gameFrame = new JFrame("Xonix");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JFrame.setDefaultLookAndFeelDecorated(true);

        Dimension d = getViewDimension(0.7f);
        View view = new View(d);
        gameFrame.add(view);

        gameFrame.pack();
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        Engine e = new Engine(view, new Xonix(d, 1));
        e.start();
    }

    private static Dimension getViewDimension(float ratio) {
        Rectangle r = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();
        return new Dimension((int)(ratio * r.getWidth()), (int)(ratio * r.getHeight()));
    }
}
