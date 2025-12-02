package com.texteditor;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.texteditor.config.GUIConfig;
import com.texteditor.manager.DocumentManager;

import javax.swing.*;
import java.util.Collections;

public class TextEditor {
    public static void main(String[] args) {
        /* Configure OS-specific GUI properties */
        GUIConfig.configure();

        FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#F00"));
        FlatMacDarkLaf.setup();

        /* Initialize Text Editor */
        SwingUtilities.invokeLater( () -> DocumentManager.getInstance().newDocument() );
    }
}
