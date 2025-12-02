package com.texteditor;

import com.texteditor.controller.tools.StatusBarTool;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.BadLocationException;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StatusBarToolTest {
    private TextEditorView view;
    private TextDocumentModel model;
    private StatusBarTool statusBarTool;

    @Test
    void checkCharacterCount() throws BadLocationException {
        model = new TextDocumentModel();

        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        view = new TextEditorView();
        view.getEditorPane().setDocument(model.getDocument());

        statusBarTool = new StatusBarTool(view, model);

        model.getDocument().insertString(0, "Hello World", null);

        statusBarTool.update();
        assertEquals(11, view.getEditorStatusBar().getCharCount());
    }

    @Test
    void checkWordCount() throws BadLocationException {
        model = new TextDocumentModel();

        //FlatMacDarkLaf.setup(); /* Dummy */
        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        view = new TextEditorView();
        view.getEditorPane().setDocument(model.getDocument());

        statusBarTool = new StatusBarTool(view, model);

        model.getDocument().insertString(0, "Hello World! How are you?", null);

        statusBarTool.update();
        assertEquals(5, view.getEditorStatusBar().getWordCount());
    }

    @Test
    void checkEmpty() {
        model = new TextDocumentModel();

        //FlatMacDarkLaf.setup(); /* Dummy */
        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        view = new TextEditorView();
        view.getEditorPane().setDocument(model.getDocument());

        statusBarTool = new StatusBarTool(view, model);

        statusBarTool.update();
        assertEquals(0, view.getEditorStatusBar().getWordCount());
        assertEquals(0, view.getEditorStatusBar().getCharCount());
    }
}
