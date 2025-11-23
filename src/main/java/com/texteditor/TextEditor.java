package com.texteditor;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;
import com.texteditor.config.GUIConfig;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class TextEditor {
    public static void main(String[] args) {
        /* Configure OS-specific GUI properties */
        GUIConfig.configure();

        /* Configure Look and Feel */
        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#F00"));
        FlatMacDarkLaf.setup();

        /* Initialize Text Editor */
        SwingUtilities.invokeLater( () -> {
            TextEditorView textEditorView = new TextEditorView();
            textEditorView.setVisible(true);
        });
    }
}
