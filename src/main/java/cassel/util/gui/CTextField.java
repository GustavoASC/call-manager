/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.Dimension;
import javax.swing.JTextField;

/**
 * Cassel field
 */
public class CTextField extends JTextField {

    public CTextField() {
        Dimension d = new Dimension(200, 20);
        setSize(d);
        setPreferredSize(d);
        addFocusListener(new ColoredFocusListener(this));
        addFocusListener(new SelectAllFocusListener(this));
        addKeyListener(new ActionFocusMover());
    }

    @Override
    public String getText() {
        return super.getText().trim();
    }

}
