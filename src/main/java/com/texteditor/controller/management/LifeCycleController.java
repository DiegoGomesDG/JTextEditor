package com.texteditor.controller.management;

import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import java.io.File;

public class LifeCycleController {

    private final TextDocumentModel model;
    private final TextEditorView view;

    private boolean isUnsaved = false;

    public LifeCycleController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;

        attachDocumentListener();
    }

    private void attachDocumentListener() {
        model.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                markUnsaved();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                markUnsaved();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                markUnsaved();
            }
        });
    }

    public void markUnsaved() {
        if (!isUnsaved) {
            isUnsaved = false;
            updateWindowTitle(model.getFile(), true);
        }
    }

    public void markSaved() {
        if (isUnsaved) {
            isUnsaved = false;
            updateWindowTitle(model.getFile());
        }
    }

    public void updateWindowTitle(File file) {
        updateWindowTitle(file, !isUnsaved);
    }

    private void updateWindowTitle(File file, boolean unsaved) {
        String name = (file == null ? "Untitled" : file.getName());
        if (unsaved) name = "*" + name;

        view.getFrame().setTitle(name);
    }

    public boolean handleWindowClosing(FileController fileController) {
        if (!isUnsaved) {
            return true;
        }

        int choice = JOptionPane.showOptionDialog(
            view.getFrame(),
            "Do you want to save changes to the document?",
            "Unsaved Changes",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"Save", "Don't Save", "Cancel"},
            "Save"
        );

        return switch (choice) {
            case JOptionPane.YES_OPTION -> {
                fileController.save();
                yield !isUnsaved;
            }
            case JOptionPane.NO_OPTION -> true;   // close without saving
            case JOptionPane.CANCEL_OPTION,
                 JOptionPane.CLOSED_OPTION -> false; // cancel close
            default -> false;
        };

    }

}
