package com.texteditor;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.texteditor.config.GUIConfig;
import com.texteditor.controller.TextEditorController;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import java.util.Collections;

public class TextEditor {
    public static void main(String[] args) {
        /* Configure OS-specific GUI properties */
        GUIConfig.configure();

        /* Configure Look and Feel */
        //UIManager.put("ScrollPane.focusWidth", 0);
        //UIManager.put("ScrollPane.innerFocusWidth", 0);
        //UIManager.put("ScrollPane.innerOutlineWidth", 0);


        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#F00"));
        FlatMacDarkLaf.setup();

        /* Initialize Text Editor */
        SwingUtilities.invokeLater( () -> {
            TextEditorView view = new TextEditorView();
            TextDocumentModel model = new TextDocumentModel();
            TextEditorController controller = new TextEditorController(model, view);
            view.setVisible(true);
        });
    }
}
