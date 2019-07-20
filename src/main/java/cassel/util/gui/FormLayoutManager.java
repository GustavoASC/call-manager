/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

/**
 * LayoutManager to group form fields with the associated column.
 */
public class FormLayoutManager implements LayoutManager {

    /* Default first cell number */
    private static final int FIRST_DEFAULT_COLUMN = 10;
    /* Default first cell number */
    private static final int SECOND_DEFAULT_COLUMN = 300;
    /* Default first cell number */
    private static final int THIRD_DEFAULT_COLUMN = 500;
    /* Cell width in pixels */
    private static final int CELL_WIDTH = 10;
    /* Cell height in pixels */
    private static final int CELL_HEIGHT = 25;

    /* First column cell */
    private final int firstColumn;
    /* Second column cell */
    private final int secondColumn;
    /* Third column cell */
    private final int thirdColumn;
    /* Number of vertical groups */
    private final int verticalGroups;

    /**
     * Creates a new form layout manager with 3 vertical groups
     */
    public FormLayoutManager() {
        this(2);
    }

    /**
     * Creates a new form layout manager with specified vertical groups
     *
     * @param verticalGroups number of vertical groups
     */
    public FormLayoutManager(int verticalGroups) {
        this.firstColumn = FIRST_DEFAULT_COLUMN;
        this.secondColumn = SECOND_DEFAULT_COLUMN;
        this.thirdColumn = THIRD_DEFAULT_COLUMN;
        this.verticalGroups = verticalGroups;
    }

    @Override
    public void addLayoutComponent(String parent, Component arg1) {

    }

    @Override
    public void removeLayoutComponent(Component parent) {

    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        return calculateParentDimension(parent);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return calculateParentDimension(parent);
    }

    private Dimension calculateParentDimension(Container parent) {
        int yIndex = 1;
        int xIndex = 1;
        int nComps = parent.getComponentCount();
        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                if (yIndex > verticalGroups) {
                    yIndex = 1;
                    xIndex++;
                }
                yIndex++;
            }
        }
        int width = verticalGroups * CELL_WIDTH;
        int height = xIndex * CELL_HEIGHT;
        return new Dimension(width, height);
    }

    @Override
    public void layoutContainer(Container parent) {
        int nComps = parent.getComponentCount();
        int yIndex = 1;
        int xIndex = 0;
        for (int i = 0; i < nComps; i++) {
            Component c = parent.getComponent(i);
            if (c.isVisible()) {
                if (yIndex > verticalGroups) {
                    yIndex = 1;
                    xIndex++;
                }
                // Set the component's size and position.
                int y = calculateComponentXInPixels(xIndex);
                int x = calculateComponentYInPixels(yIndex);
                Dimension d = c.getPreferredSize();
                c.setBounds(x, y, d.width, d.height);
                yIndex++;
            }
        }
    }

    /**
     * Calculates the component Y position within row
     *
     * @return component Y position
     */
    private int calculateComponentYInPixels(int yIndex) {
        int indexInPixels = yIndex * CELL_WIDTH;
        switch (yIndex) {
            case 1:
                return indexInPixels + firstColumn;
            case 2:
                return indexInPixels + secondColumn;
            case 3:
                return indexInPixels + thirdColumn;
        }
        return 0;
    }

    /**
     * Calculates the component X position in pixels
     *
     * @return component X position
     */
    private int calculateComponentXInPixels(int xIndex) {
        return xIndex * CELL_HEIGHT;
    }

}
