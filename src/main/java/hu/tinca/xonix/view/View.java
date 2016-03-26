package hu.tinca.xonix.view;

import javax.swing.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyEvent;

/**
 *
 */
public class View extends JPanel {

    public View(Dimension d) {
        setPreferredSize(d);
        init();
    }

    private void init() {
        InputMap im = getInputMap(WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), "left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), "right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), "up");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), "down");

//        am.put("left", new MoveAction(pacMan, -4, 0));
//        am.put("right", new MoveAction(pacMan, 4, 0));
//        am.put("up", new MoveAction(pacMan, 0, -4));
//        am.put("down", new MoveAction(pacMan, 0, 4));
    }

}
