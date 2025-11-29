package com.texteditor.controller.services;

import com.texteditor.model.TextDocumentModel;

import javax.swing.text.DefaultStyledDocument;
import java.io.*;

public class LoadSaveService {
    private final TextDocumentModel model;

    public LoadSaveService(TextDocumentModel document) {
        this.model = document;
    }
    /**
     * Saves the current StyledDocument to a .ser file.
     */
    public void save(File file) throws IOException {
        DefaultStyledDocument doc = model.getDocument();

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(doc);
        }
    }

    /**
     * Loads a StyledDocument from a .ser file and replaces the current document.
     */
    public void load(File file) throws IOException, ClassNotFoundException {
        DefaultStyledDocument loaded;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            loaded = (DefaultStyledDocument) in.readObject();
        }

        // Replace model document
        model.setDocument(loaded);
    }
}
