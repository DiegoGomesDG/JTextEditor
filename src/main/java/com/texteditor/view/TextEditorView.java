package com.texteditor.view;

import com.texteditor.config.GUIConfig;
import com.texteditor.view.components.*;

import javax.swing.*;
import java.awt.*;

/* https://www.formdev.com/flatlaf/macos/ */

public class TextEditorView extends JFrame {

    private EditorToolBar textEditorToolBar;
    private EditorStatusBar statusBar;
    private EditorPane editorPane;
    private EditorMenuBar menuBar;

    public TextEditorView(String title) {
        setTitle(title);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(950, 600);
        setMinimumSize(new Dimension(870, 300));
        setResizable(true);
        setLocationRelativeTo(null); /* Center the window */

        /* Create Components */
        textEditorToolBar = new EditorToolBar();
        statusBar = new EditorStatusBar();
        editorPane = new EditorPane();
        menuBar = new EditorMenuBar();

        setLayout(new BorderLayout());

        /* Add to the layout */
        add(textEditorToolBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(editorPane, BorderLayout.CENTER);

        setJMenuBar(menuBar);

        /* Call the method to set specific MacOS GUI features */
        GUIConfig.configureMacOSWindow(this);
    }

    public TextEditorView() {
        this("Document 1");
    }

    public JEditorPane getEditorPane() {
        return editorPane;
    }

    public EditorToolBar getTextEditorToolBar() {
        return textEditorToolBar;
    }

}
