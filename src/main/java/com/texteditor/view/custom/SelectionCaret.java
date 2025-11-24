package com.texteditor.view.custom;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.event.FocusEvent;

public class SelectionCaret extends DefaultCaret {
    public SelectionCaret()
    {
        setBlinkRate(UIManager.getInt("TextField.caretBlinkRate"));
    }

    @Override
    public void focusGained(FocusEvent e)
    {
        setVisible(true);
        setSelectionVisible(true);
    }

    @Override
    public void focusLost(FocusEvent e)
    {
        setVisible(false);
    }
}
