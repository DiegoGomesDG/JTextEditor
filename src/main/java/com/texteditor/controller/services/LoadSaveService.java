package com.texteditor.controller.services;

import com.texteditor.model.TextDocumentModel;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.EditorKit;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;

public class LoadSaveService {

    public static void save(TextDocumentModel model, File file)
        throws IOException, BadLocationException {

        EditorKit kit = new RTFEditorKit();
        DefaultStyledDocument doc = model.getDocument();

        try (OutputStream out = new FileOutputStream(file)) {
            kit.write(out, doc, 0, doc.getLength());
        }
    }

    public static void load(TextDocumentModel model, File file)
        throws IOException, BadLocationException {

        EditorKit kit = new RTFEditorKit();

        // Always create a new empty document from the same EditorKit
        DefaultStyledDocument newDoc =
            (DefaultStyledDocument) kit.createDefaultDocument();

        try (InputStream in = new FileInputStream(file)) {
            kit.read(in, newDoc, 0);
        }

        // Replace the model’s document
        model.setDocument(newDoc);
    }
}
