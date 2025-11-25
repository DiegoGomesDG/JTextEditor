package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.event.ActionEvent;

public class ChangeFontAction extends StyledEditorKit.StyledTextAction {

    private final JEditorPane editor;

    public ChangeFontAction(JEditorPane editor) {
        super("change-font");
        this.editor = editor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (editor == null) return;
        String family = null;

        if (e.getSource() instanceof JComboBox<?> combo) {
            Object item = combo.getSelectedItem();
            if (item != null)
                family = item.toString();
        }

        if (family == null || family.isEmpty())
            return;

        MutableAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setFontFamily(attrs, family);
        setCharacterAttributes(editor, attrs, false);

    }
}
