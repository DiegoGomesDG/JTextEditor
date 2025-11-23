package com.texteditor;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.SystemInfo;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;

public class TextEditor {
    public static void main(String[] args) {

        /* MacOS Specific GUI Customization
        * https://www.formdev.com/flatlaf/macos/
        * */
        if (SystemInfo.isMacOS) {
            /* Enable Screen Menu Bar */
            System.setProperty("apple.laf.useScreenMenuBar", "true");

            /* Java Text Editor in the screen menu bar*/
            System.setProperty("apple.awt.application.name", "Java Text Editor");

            /* Appearance of Window Title Bars */
            System.setProperty("apple.awt.application.appearance", "system");
        }
        if (SystemInfo.isLinux) {
            JFrame.setDefaultLookAndFeelDecorated(true);
            JDialog.setDefaultLookAndFeelDecorated(true);
        }

        SwingUtilities.invokeLater( () -> {
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#F00"));
            FlatMacDarkLaf.setup();
            UIManager.put("ToolBar.background", new Color(40,40,40));

            /* TextEditor Frame */
            TextEditorView textEditorView = new TextEditorView();
            textEditorView.setVisible(true);
        });
    }


}
