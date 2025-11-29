package com.texteditor.controller.tools;

import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyledDocument;

public class StatusBarTool {
    private final TextEditorView view;
    private final TextDocumentModel model;

    public StatusBarTool(TextEditorView view, TextDocumentModel model) {
        this.view = view;
        this.model = model;

        setupListeners();
        update();
    }

    private void setupListeners() {
        StyledDocument document = model.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { update(); }

            @Override
            public void removeUpdate(DocumentEvent e) { update(); }

            @Override
            public void changedUpdate(DocumentEvent e) { update(); }
        });
    }

    private void update() {
        String text = view.getEditorPane().getText();
        int charCount = text.length();

        String trimmed = text.trim();
        int wordCount = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;

        view.getEditorStatusBar().setCount(wordCount, charCount);
    }
}
