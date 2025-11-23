package com.texteditor.config;

import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;

public class GUIConfig {
    private GUIConfig() {}

    public static void configure() {
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
    }

    public static void configureMacOSWindow(JFrame frame) {
        if(!SystemInfo.isMacOS) {
            return;
        }

        JRootPane rootPane = frame.getRootPane();
        if(SystemInfo.isMacFullWindowContentSupported) {

            /* Expand window content into window title bar and make title bar transparent*/
            //rootPane.putClientProperty("apple.awt.fullWindowContent", true);
            rootPane.putClientProperty("apple.awt.transparentTitleBar", true);
            //rootPane.putClientProperty( FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING, FlatClientProperties.MACOS_WINDOW_BUTTONS_SPACING_LARGE );

            /* Hide window title */
            if (SystemInfo.isJava_17_orLater) {
                //rootPane.putClientProperty("apple.awt.windowTitleVisible", false);
            } else {
                //frame.setTitle(null);
            }
        }

        /* Enable full screen mode for the window */
        if (!SystemInfo.isJava_11_orLater) {
            rootPane.putClientProperty("apple.awt.fullscreenable", true);
        }
    }
}
