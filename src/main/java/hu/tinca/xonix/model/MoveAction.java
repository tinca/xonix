package hu.tinca.xonix.model;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 */
public class MoveAction extends AbstractAction {
    private Lead lead;
    private Direction direction;

    public MoveAction(Lead lead, Direction direction) {
        this.lead = lead;
        this.direction = direction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        lead.changeDirection(direction);
        lead.advance();
    }

}
