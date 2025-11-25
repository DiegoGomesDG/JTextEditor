package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.*;

public class TextColorAction extends StyledColorAction {

    public TextColorAction(JEditorPane editor) { super("font-color", editor); }

    @Override
    protected void applyColor(SimpleAttributeSet attrs) {
        StyleConstants.setForeground(attrs, color);
    }
}
