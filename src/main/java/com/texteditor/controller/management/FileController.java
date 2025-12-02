package com.texteditor.controller.management;

import com.texteditor.actions.FileAction;
import com.texteditor.controller.services.LoadSaveService;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

public class FileController {
    private final TextDocumentModel model;
    private final TextEditorView view;
    private final LifeCycleController lifecycle;

    public FileController(TextDocumentModel model, TextEditorView view, LifeCycleController lifecycle) {
        this.model = model;
        this.view = view;
        this.lifecycle = lifecycle;

        //attachListeners();
    }

    private void attachListeners() {
        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.OPEN_DOCUMENT).addActionListener(
            e -> openDocument()
        );

        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.SAVE).addActionListener(
            e -> save()
        );

        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.SAVE_AS).addActionListener(
            e -> saveAs()
        );
    }

    public void openDocument() {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Rich Text Format (*.rtf)", "rtf"));
        chooser.setDialogTitle("Open Document");

        if (chooser.showOpenDialog(view.getFrame()) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();

        try {
            LoadSaveService.load(model, file);

            model.setFile(file);
            view.getEditorPane().setDocument(model.getDocument());

            lifecycle.markSaved();
            lifecycle.updateWindowTitle(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(),
                "Failed to open document:\n" + e.getMessage(),
                "Open Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void openDocument(File file) {
        try {
            LoadSaveService.load(model, file);

            model.setFile(file);
            view.getEditorPane().setDocument(model.getDocument());

            lifecycle.markSaved();
            lifecycle.updateWindowTitle(file);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(),
                "Failed to open document:\n" + e.getMessage(),
                "Open Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveAs() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save As");
        chooser.setFileFilter(new FileNameExtensionFilter("Rich Text Format (*.rtf)", "rtf"));

        if (chooser.showSaveDialog(view.getFrame()) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();

        /* Add the .rtf extension if it not added yet */
        if (!file.getName().toLowerCase().endsWith(".rtf")) {
            file = new File(file.getAbsolutePath() + ".rtf");
        }

        try {
            LoadSaveService.save(model, file);
            model.setFile(file);
            lifecycle.markSaved();
            lifecycle.updateWindowTitle(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(),
                "Failed to save document:\n" + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void save() {
        File file = model.getFile();

        if (file == null) {
            saveAs();
            return;
        }

        try {
            LoadSaveService.save(model, file);

            lifecycle.markSaved();
            lifecycle.updateWindowTitle(file);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view.getFrame(),
                "Failed to save document:\n" + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public File getCurrentFile() {
        return model.getFile();
    }
}
