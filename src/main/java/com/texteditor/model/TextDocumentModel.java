package com.texteditor.model;

import com.texteditor.editorkit.scaling.ScaledEditorKit;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledEditorKit;

public class TextDocumentModel {

    private final DefaultStyledDocument document;
    private final ScaledEditorKit editorKit;

    public TextDocumentModel() {
        this.editorKit = new ScaledEditorKit();
        this.document = (DefaultStyledDocument) editorKit.createDefaultDocument();
    }

    public DefaultStyledDocument getDocument() {
        return document;
    }

    public ScaledEditorKit getEditorKit() {
        return editorKit;
    }
}
