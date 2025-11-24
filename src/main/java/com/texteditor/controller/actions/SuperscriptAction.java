package com.texteditor.controller.actions;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;

public class SuperscriptAction extends StyledEditorKit.StyledTextAction {
    public SuperscriptAction() {
        super("font-superscript");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editor = getEditor(e);
        if (editor == null) {
            return;
        }

        StyledDocument document = (StyledDocument) editor.getDocument();
        int start = editor.getSelectionStart();
        int end = editor.getSelectionEnd();

        if (start == end) return; // no selection → do nothing

        // Get the existing style
        Element elem = document.getCharacterElement(start);
        AttributeSet old = elem.getAttributes();

        boolean currentlyOn = StyleConstants.isSuperscript(old);
        boolean newValue = !currentlyOn;

        // Create new style with toggled strikethrough
        SimpleAttributeSet sas = new SimpleAttributeSet();
        StyleConstants.setSuperscript(sas, newValue);

        // Apply style
        document.setCharacterAttributes(start, end - start, sas, false);
    }
}
