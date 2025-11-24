package com.texteditor.model;

import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.StyledEditorKit;

public class TextDocumentModel {

    private final DefaultStyledDocument document;
    private final StyledEditorKit editorKit;

    public TextDocumentModel() {
        this.editorKit = new StyledEditorKit();
        this.document = (DefaultStyledDocument) editorKit.createDefaultDocument();
    }

    public DefaultStyledDocument getDocument() {
        return document;
    }

    public StyledEditorKit getEditorKit() {
        return editorKit;
    }
}
