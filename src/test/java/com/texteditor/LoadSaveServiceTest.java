package com.texteditor;

import com.texteditor.controller.services.LoadSaveService;
import com.texteditor.model.TextDocumentModel;
import org.junit.jupiter.api.Test;

import javax.swing.text.*;
import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class LoadSaveServiceTest {
    @Test
    void testSaveLoadPlainText() throws Exception {
        TextDocumentModel model = new TextDocumentModel();

        DefaultStyledDocument doc = new DefaultStyledDocument();
        doc.insertString(0, "Test Unformatted Text", null);

        model.setDocument(doc);

        File tmp = File.createTempFile("test", ".rtf");

        LoadSaveService.save(model, tmp);
        LoadSaveService.load(model, tmp);

        String loaded = model.getDocument().getText(0, model.getDocument().getLength());

        assertEquals("Test Unformatted Text\n", loaded);
    }

    @Test
    void testSaveLoadFormatting() throws Exception {
        TextDocumentModel model = new TextDocumentModel();
        DefaultStyledDocument doc = new DefaultStyledDocument();

        doc.insertString(0, "Test Formatted Text", null);

        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setBold(attrs, true);
        StyleConstants.setItalic(attrs, true);
        StyleConstants.setForeground(attrs, Color.RED);
        StyleConstants.setFontSize(attrs, 24);
        doc.setCharacterAttributes(0, 19, attrs, false);

        model.setDocument(doc);

        File tmp = File.createTempFile("test-format", ".rtf");

        LoadSaveService.save(model, tmp);
        LoadSaveService.load(model, tmp);

        StyledDocument loaded = model.getDocument();
        AttributeSet out = loaded.getCharacterElement(0).getAttributes();

        assertTrue(StyleConstants.isBold(out));
        assertTrue(StyleConstants.isItalic(out));
        assertEquals(Color.RED, StyleConstants.getForeground(out));
        assertEquals(24, StyleConstants.getFontSize(out));
    }

    @Test
    void testSaveLoadEmptyDocument() throws Exception {
        TextDocumentModel model = new TextDocumentModel();

        DefaultStyledDocument doc = new DefaultStyledDocument();
        model.setDocument(doc);

        File tmp = File.createTempFile("empty", ".rtf");

        LoadSaveService.save(model, tmp);
        LoadSaveService.load(model, tmp);

        assertEquals(1, model.getDocument().getLength());
    }
}
