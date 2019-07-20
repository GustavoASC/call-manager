/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.FlowLayout;
import java.awt.ScrollPane;
import javax.swing.JScrollPane;

/**
 * A field with an associated label.
 *
 * <p>
 * This class makes easier to create a group consisting of one label and one
 * text field.
 */
public class CLabeledArea extends CPanel {

    /* Field label */
    private final CLabel label;
    /* Field itself */
    private final CTextArea area;

    /**
     * Creates a field with an associated label
     *
     * @param label label text
     */
    public CLabeledArea(String label) {
        this.label = new CLabel(label);
        this.area = new CTextArea();
        this.area.setLineWrap(true);
        this.area.setWrapStyleWord(true);
        configurePanel();
    }

    /**
     * Configures the panel
     */
    private void configurePanel() {
        setLayout(new FlowLayout());
        // Label
        add(label);
        // Scroll part
        add(new CScrollPane(area));
    }

    /**
     * Returns the label instance
     *
     * @return label
     */
    public CLabel getLabel() {
        return label;
    }

    /**
     * Returns the text field instance
     *
     * @return field instance
     */
    public CTextArea getArea() {
        return area;
    }

}
