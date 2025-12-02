package com.texteditor.model;

import com.texteditor.editorkit.scaling.ScaledEditorKit;

import javax.swing.text.DefaultStyledDocument;
import java.io.File;

public class TextDocumentModel {

    private DefaultStyledDocument document;
    private ScaledEditorKit editorKit;
    private File file;

    public TextDocumentModel() {
        this.editorKit = new ScaledEditorKit();
        this.document = (DefaultStyledDocument) editorKit.createDefaultDocument();
        this.file = null;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public DefaultStyledDocument getDocument() {
        return document;
    }

    public ScaledEditorKit getEditorKit() {
        return editorKit;
    }

    public void setDocument(DefaultStyledDocument document) {
        this.document = document;
    }
}
