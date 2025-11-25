package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;

public abstract class StyledColorAction extends StyledEditorKit.StyledTextAction {

    protected final JEditorPane editor;
    protected Color color;

    protected StyledColorAction(String name, JEditorPane editor) {
        super(name);
        this.editor = editor;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    /** Subclass implements how color is applied */
    protected abstract void applyColor(SimpleAttributeSet attrs);

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor == null || color == null) return;

        int start = editor.getSelectionStart();
        int end = editor.getSelectionEnd();
        if (start == end) return;

        SimpleAttributeSet attrs = new SimpleAttributeSet();
        applyColor(attrs);   // delegate to subclass

        StyledDocument doc = (StyledDocument) editor.getDocument();
        doc.setCharacterAttributes(start, end - start, attrs, false);
    }
}
