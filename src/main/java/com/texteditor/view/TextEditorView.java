package com.texteditor.view;

import com.texteditor.config.GUIConfig;
import com.texteditor.view.components.*;

import javax.swing.*;
import java.awt.*;

/* https://www.formdev.com/flatlaf/macos/ */

public class TextEditorView extends JFrame {

    private EditorToolBar editorToolBar;
    private EditorStatusBar editorStatusBar;
    private EditorPane editorPane;
    private EditorMenuBar editorMenuBar;

    public TextEditorView() {
        setTitle("Untitled");
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(950, 600);
        setMinimumSize(new Dimension(870, 300));
        setResizable(true);
        setLocationRelativeTo(null); /* Center the window */

        /* Create Components */
        editorToolBar = new EditorToolBar();
        editorStatusBar = new EditorStatusBar();
        editorPane = new EditorPane();
        editorMenuBar = new EditorMenuBar();

        JScrollPane scroll = new JScrollPane(editorPane);
        setLayout(new BorderLayout());

        /* Add to the layout */
        add(editorToolBar, BorderLayout.NORTH);
        add(editorStatusBar, BorderLayout.SOUTH);
        add(scroll, BorderLayout.CENTER);

        setJMenuBar(editorMenuBar);

        /* Call the method to set specific MacOS GUI features */
        GUIConfig.configureMacOSWindow(this);
    }

    public JFrame getFrame() {
        return this;
    }


    public JEditorPane getEditorPane() {
        return editorPane;
    }

    public EditorToolBar getEditorToolBar() {
        return editorToolBar;
    }

    public EditorStatusBar getEditorStatusBar() {
        return editorStatusBar;
    }

    public EditorMenuBar getEditorMenuBar() {
        return editorMenuBar;
    }

}
