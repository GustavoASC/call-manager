/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.JTextComponent;

/**
 * Listener to select all data from field
 *
 * @author gustavo
 */
public class SelectAllFocusListener implements FocusListener {

    /* Target field */
    private final JTextComponent field;

    /**
     * Creates new listener
     *
     * @param field
     */
    public SelectAllFocusListener(JTextComponent field) {
        this.field = field;
    }

    @Override
    public void focusGained(FocusEvent e) {
        field.selectAll();
    }

    @Override
    public void focusLost(FocusEvent e) {

    }

}
