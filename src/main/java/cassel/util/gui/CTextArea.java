/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import javax.swing.JTextArea;

/**
 * Cassel text area
 */
public class CTextArea extends JTextArea {

    public CTextArea() {
        addFocusListener(new ColoredFocusListener(this));
        addKeyListener(new ActionFocusMover());
    }

}
