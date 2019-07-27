/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cassel.util.gui;

import java.awt.KeyboardFocusManager;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Action to move focus with enter and esc
 *
 * @author gustavo
 */
public class ActionFocusMover extends KeyAdapter {

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusNextComponent();
        }
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
            manager.focusPreviousComponent();
        }

    }

}
