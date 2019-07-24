/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JScrollPane;

/**
 * Scroll pane
 */
public class CScrollPane extends JScrollPane {

    public CScrollPane(Component view) {
        super(view);
        Dimension d = new Dimension(502, 120);
        setSize(d);
        setPreferredSize(d);
    }

    public CScrollPane() {
        Dimension d = new Dimension(502, 120);
        setSize(d);
        setPreferredSize(d);
    }
}
