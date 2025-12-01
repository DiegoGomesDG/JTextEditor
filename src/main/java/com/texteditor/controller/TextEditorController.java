package com.texteditor.controller;

import com.texteditor.actions.FileAction;
import com.texteditor.controller.services.LoadSaveService;
import com.texteditor.controller.tools.FindTool;
import com.texteditor.controller.tools.FormattingTool;
import com.texteditor.controller.tools.StatusBarTool;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;
import com.texteditor.controller.tools.ZoomTool;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;

/* Some discussions concerning separation of concerns for the MVC Architecture
* https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
*
*/
public class TextEditorController {

    private final TextDocumentModel model;
    private final TextEditorView view;

    private boolean saveStatus;

    public TextEditorController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;
        this.saveStatus = true; /* An empty document is saved/empty */

        view.getEditorPane().setEditorKit(model.getEditorKit());
        view.getEditorPane().setDocument(model.getDocument());

        /* Add listener for the document changes */
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

        /* Setting up the different actions and dynamic components */
        new FormattingTool(view);
        new StatusBarTool(view, model);
        new ZoomTool(view, model);
        new FindTool(view, model.getDocument());

        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.OPEN_DOCUMENT).addActionListener(
            e -> openDocument(view.getFrame())
        );

        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.SAVE).addActionListener(
            e -> save(view.getFrame())
        );

        view.getEditorMenuBar().getFileMenu().getMenuItem(FileAction.SAVE_AS).addActionListener(
            e -> saveAs(view.getFrame())
        );

        view.getFrame().addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                handleWindowClosing();
            }
        });

    }

    private void markUnsaved() {
        if (saveStatus) { // only update title when transitioning from clean → dirty
            saveStatus = false;
            updateWindowTitle(model.getFile(), true);
        }
    }

    private void handleWindowClosing() {
        if (!saveStatus) {
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

            if (choice == JOptionPane.YES_OPTION) {
                save(view.getFrame());
                if (saveStatus) { // only close if save succeeded
                    System.exit(0);
                }
            } else if (choice == JOptionPane.NO_OPTION) {
                System.exit(0);
            } else {
                /* If the user presses Cancel, it should continue on the program */
            }
        } else {
            System.exit(0);
        }
    }

    public void openDocument(JFrame parent) {

        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open Document");

        if (chooser.showOpenDialog(parent) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();

        try {
            LoadSaveService.load(model, file);
            model.setFile(file);
            view.getEditorPane().setDocument(model.getDocument());
            updateWindowTitle(file);
            saveStatus = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                "Failed to open document:\n" + e.getMessage(),
                "Open Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveAs(JFrame parent) {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Save As");
        chooser.setFileFilter(new FileNameExtensionFilter("Rich Text Format (*.rtf)", "rtf"));

        if (chooser.showSaveDialog(parent) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();

        /* Add the .rtf extension if it not added yet */
        if (!file.getName().toLowerCase().endsWith(".rtf")) {
            file = new File(file.getAbsolutePath() + ".rtf");
        }

        try {
            LoadSaveService.save(model, file);
            model.setFile(file);
            updateWindowTitle(file);
            saveStatus = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                "Failed to save document:\n" + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public void save(JFrame parent) {
        File file = model.getFile();

        if (file == null) {
            saveAs(parent);
            updateWindowTitle(file);
            saveStatus = true;
            return;
        }

        try {
            LoadSaveService.save(model, file);
            saveStatus = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(parent,
                "Failed to save document:\n" + e.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateWindowTitle(File file) {
        updateWindowTitle(file, !saveStatus);
    }

    private void updateWindowTitle(File file, boolean dirty) {
        String name = (file == null ? "Untitled" : file.getName());
        if (dirty) name = "*" + name;
        view.getFrame().setTitle(name);
    }
}
