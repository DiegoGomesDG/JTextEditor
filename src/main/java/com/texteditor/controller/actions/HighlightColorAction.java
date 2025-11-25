package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

public class HighlightColorAction extends StyledColorAction {

    public HighlightColorAction(JEditorPane editor) {
        super("font-color", editor);
    }

    @Override
    protected void applyColor(SimpleAttributeSet attrs) {
        StyleConstants.setBackground(attrs, color);
    }
}
