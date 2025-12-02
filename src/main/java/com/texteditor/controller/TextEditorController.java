package com.texteditor.controller;

import com.texteditor.actions.EditAction;
import com.texteditor.controller.management.FileController;
import com.texteditor.controller.management.LifeCycleController;
import com.texteditor.controller.tools.FindTool;
import com.texteditor.controller.tools.FormattingTool;
import com.texteditor.controller.tools.StatusBarTool;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;
import com.texteditor.controller.tools.ZoomTool;

import javax.swing.undo.UndoManager;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/* Some discussions concerning separation of concerns for the MVC Architecture
* https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
*/
public class TextEditorController {

    private final TextDocumentModel model;
    private final TextEditorView view;

    private final FileController fileController;
    private final LifeCycleController lifeCycleController;

    private UndoManager undoManager;

    public TextEditorController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;

        view.getEditorPane().setEditorKit(model.getEditorKit());
        view.getEditorPane().setDocument(model.getDocument());

        /* Create the FileController and the LifeCycleController */
        this.lifeCycleController = new LifeCycleController(model, view);
        this.fileController = new FileController(model, view, lifeCycleController);

        /* Setting up the different actions and dynamic components */
        new FormattingTool(view);
        new StatusBarTool(view, model);
        new ZoomTool(view, model);
        new FindTool(view, model.getDocument());

        /* Setup Undo Manager */
        undoManager = new UndoManager();
        setupUndoManager();

        /* Add window listener to allow the user to close the program or prompt for saving or discard the file */
        setupWindowListener();
    }

    private void setupWindowListener() {
        view.getFrame().addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (lifeCycleController.handleWindowClosing(fileController)) {
                    view.getFrame().dispose(); // or manager.unregister(...)
                }
            }
        });
    }

    private void setupUndoManager() {
        view.getEditorPane().getDocument().addUndoableEditListener(undoManager);

        view.getEditorMenuBar().getEditMenu().getMenuItem(EditAction.UNDO).addActionListener(
            e -> undoManager.undo()
        );
        view.getEditorMenuBar().getEditMenu().getMenuItem(EditAction.REDO).addActionListener(
            e -> undoManager.redo()
        );
    }
}
