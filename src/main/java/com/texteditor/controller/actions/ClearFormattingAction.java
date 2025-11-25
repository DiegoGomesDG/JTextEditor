package com.texteditor.controller.actions;

import com.texteditor.model.TextDefaults;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;

public class ClearFormattingAction extends StyledEditorKit.StyledTextAction{

    public ClearFormattingAction() {
        super("clear-formatting");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JEditorPane editor = getEditor(e);
        if (editor == null) return;

        StyledDocument document = (StyledDocument) editor.getDocument();
        int start = editor.getSelectionStart();
        int end = editor.getSelectionEnd();

        if (start == end) return;

        // Reset attributes to default
        SimpleAttributeSet sas = new SimpleAttributeSet();

        // Default font
        StyleConstants.setFontFamily(sas, TextDefaults.FONT_FAMILY);
        StyleConstants.setFontSize(sas, TextDefaults.FONT_SIZE);

        // Default color
        StyleConstants.setForeground(sas, TextDefaults.FONT_COLOR);

        // Remove highlight (background)
        sas.removeAttribute(StyleConstants.Background);

        // Default toggles
        StyleConstants.setBold(sas, false);
        StyleConstants.setItalic(sas, false);
        StyleConstants.setUnderline(sas, false);
        StyleConstants.setStrikeThrough(sas, false);
        StyleConstants.setSuperscript(sas, false);
        StyleConstants.setSubscript(sas, false);

        // Default alignment
        StyleConstants.setAlignment(sas, TextDefaults.TEXT_ALIGNMENT);

        document.setCharacterAttributes(start, end - start, sas, true);
    }
}
