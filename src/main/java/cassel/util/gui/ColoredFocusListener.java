/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;

/**
 * Focus listener which colors the field
 */
public class ColoredFocusListener implements FocusListener {

    /* Target field */
    private final JTextComponent field;

    /**
     * Creates a listener for the specified field
     *
     * @param field
     */
    public ColoredFocusListener(JTextComponent field) {
        this.field = field;
    }

    @Override
    public void focusGained(FocusEvent e) {
        field.setBackground(Color.YELLOW);
    }

    @Override
    public void focusLost(FocusEvent e) {
        field.setBackground(Color.WHITE);
    }

}
