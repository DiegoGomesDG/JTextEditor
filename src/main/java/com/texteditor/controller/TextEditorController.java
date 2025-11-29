package com.texteditor.controller;

import com.texteditor.controller.tools.FormattingTool;
import com.texteditor.controller.tools.StatusBarTool;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;
import com.texteditor.controller.tools.ZoomTool;
import com.texteditor.view.custom.SearchDialog;

import javax.swing.*;

/* Some discussions concerning separation of concerns for the MVC Architecture
* https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
*
*/
public class TextEditorController {

    private final TextDocumentModel model;
    private final TextEditorView view;

    public TextEditorController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;

        view.getEditorPane().setEditorKit(model.getEditorKit());
        view.getEditorPane().setDocument(model.getDocument());

        /* Setting up the different actions and dynamic components */
        new FormattingTool(view);
        new StatusBarTool(view, model);
        new ZoomTool(view, model);

        /* Experimental */
        JMenuItem findItem = view.getEditorMenuBar().getEditMenu().getFindMenuItem();
        findItem.addActionListener(e -> {
            SearchDialog searchDialog = new SearchDialog(view.getFrame());
            searchDialog.setVisible(true);
        });
    }

}
