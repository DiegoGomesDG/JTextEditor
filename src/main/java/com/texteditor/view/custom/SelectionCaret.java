package com.texteditor.view.custom;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.FocusEvent;

/* Special thanks for the user camickr
 * https://stackoverflow.com/questions/43402812/how-to-edit-a-jcombobox-with-text-selected-in-jeditorpane
 * */
public class SelectionCaret extends DefaultCaret {
    public SelectionCaret() {
        setBlinkRate(UIManager.getInt("TextField.caretBlinkRate"));
    }

    @Override
    public void focusGained(FocusEvent e) {
        setVisible(true);
        setSelectionVisible(true);
    }

    @Override
    public void focusLost(FocusEvent e) {
        setVisible(false);
    }
}
