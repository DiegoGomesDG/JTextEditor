package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class FontSizeAction extends StyledEditorKit.StyledTextAction {

    private final JEditorPane editor;


    public FontSizeAction(JEditorPane editor) {
        super("font-size");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (editor == null) return;
        int newSize = -1;

        if (e.getSource() instanceof JComboBox<?> combo) {
            Object item = combo.getSelectedItem();
            try {
                newSize = (Integer) item;
            } catch (Exception ignored) {}
        }

        if (newSize <= 0) {
            UIManager.getLookAndFeel().provideErrorFeedback(editor);
            return;
        }

        // Clamp the value
        int clamped = Math.min(newSize, 250);

        // If user entered something >250, reset the ComboBox UI
        if (clamped != newSize && e.getSource() instanceof JComboBox<?> combo) {
            combo.setSelectedItem(clamped);
        }

        MutableAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontSize(attrs, newSize);
        setCharacterAttributes(editor, attrs, false);

    }
}