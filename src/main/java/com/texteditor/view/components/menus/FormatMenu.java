package com.texteditor.view.components.menus;

import javax.swing.*;

public class FormatMenu extends JMenu {

    private final JMenu textMenu;
    private final JMenu alignTextMenu;

    public FormatMenu() {
        setText("Format");

        /* textMenu */
        textMenu = new JMenu("Text");
        add(textMenu);

        /* alignTextMenu */
        alignTextMenu = new JMenu("Align Text");
        add(alignTextMenu);

    }

}
