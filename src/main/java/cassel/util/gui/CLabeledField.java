/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.FlowLayout;

/**
 * A field with an associated label.
 *
 * <p>
 * This class makes easier to create a group consisting of one label and one
 * text field.
 */
public class CLabeledField extends CPanel {

    /* Field label */
    private final CLabel label;
    /* Field itself */
    private final CTextField field;

    /**
     * Creates a field with an associated label
     *
     * @param label label text
     */
    public CLabeledField(String label) {
        this.label = new CLabel(label);
        this.field = new CTextField();
        configurePanel();
    }

    /**
     * Configures the panel
     */
    private void configurePanel() {
        setLayout(new FlowLayout());
        add(label);
        add(field);
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
    public CTextField getField() {
        return field;
    }

}
