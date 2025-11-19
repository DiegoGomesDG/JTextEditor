package com.texteditor.view;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.SystemInfo;
import com.texteditor.view.components.EditorMenuBar;
import com.texteditor.view.components.EditorPane;
import com.texteditor.view.components.EditorStatusBar;
import com.texteditor.view.components.EditorToolBar;

import javax.swing.*;
import java.awt.*;

/* https://www.formdev.com/flatlaf/macos/ */

public class TextEditorView extends JFrame {

    private EditorToolBar toolBar;
    private EditorStatusBar statusBar;
    private EditorPane editorPane;
    private EditorMenuBar menuBar;

    public TextEditorView() {
        setTitle("Java Text Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(900, 600);
        setMinimumSize(new Dimension(860, 300));
        setResizable(true);
        setLocationRelativeTo(null); /* Center the window */

        /* Create Components */
        toolBar = new EditorToolBar();
        statusBar = new EditorStatusBar();
        editorPane = new EditorPane();
        menuBar = new EditorMenuBar();

        /* Use BorderLayout */
        setLayout(new BorderLayout());

        /* Add to the layout */
        add(toolBar, BorderLayout.NORTH);
        add(statusBar, BorderLayout.SOUTH);
        add(editorPane, BorderLayout.CENTER);

        /* Set MenuBar */
        setJMenuBar(menuBar);

        if(SystemInfo.isMacOS) {
            JRootPane rootPane = getRootPane();
            if(SystemInfo.isMacFullWindowContentSupported) {
                /* Expand window content into window title bar and make title bar transparent*/
                //rootPane.putClientProperty( "apple.awt.fullWindowContent", true );
                //rootPane.putClientProperty( "apple.awt.transparentTitleBar", true );
                //rootPane.putClientProperty( FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING, FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING_LARGE );

                /* Hide window title */
                if (SystemInfo.isJava_17_orLater) {
                    //rootPane.putClientProperty("apple.awt.windowTitleVisible", false);
                } else {
                    //setTitle(null);
                }
            }

            /* Enable full screen mode for the window */
            if (!SystemInfo.isJava_11_orLater) {
                rootPane.putClientProperty("apple.awt.fullscreenable", true);
            }

        }
    }
}
