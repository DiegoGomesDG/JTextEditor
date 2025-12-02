package com.texteditor.manager;

import com.texteditor.TextEditor;
import com.texteditor.controller.TextEditorController;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DocumentWindow {
    private final TextDocumentModel model;
    private final TextEditorView view;
    private final TextEditorController controller;

    private final DocumentManager manager;

    public DocumentWindow(DocumentManager manager) {
        this.model = new TextDocumentModel();
        this.view = new TextEditorView();
        this.controller = new TextEditorController(model, view);

        this.manager = manager;

        /* Add listener to each window to communicate to the DocumentManager to close the window */
        attachWindowListener();

        view.getFrame().setVisible(true);
    }

    private void attachWindowListener() {
        view.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {

                boolean canClose =
                    controller.getLifeCycleController()
                        .handleWindowClosing(controller.getFileController());

                if (canClose) {
                    DocumentManager.getInstance().closeWindow(DocumentWindow.this);
                    view.getFrame().dispose();
                }
            }
        });
    }

    public TextDocumentModel getModel() {
        return model;
    }

    public TextEditorView getView() {
        return view;
    }

    public TextEditorController getController() {
        return controller;
    }
}
