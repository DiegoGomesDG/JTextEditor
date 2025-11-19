package com.texteditor.view.components;

import com.texteditor.view.components.menus.EditMenu;
import com.texteditor.view.components.menus.FileMenu;
import com.texteditor.view.components.menus.FormatMenu;

import javax.swing.*;

public class EditorMenuBar extends JMenuBar {

    private final FileMenu fileMenu;
    private final EditMenu editMenu;
    private final FormatMenu formatMenu;

    public EditorMenuBar() {
        /* Create the individual Menus */
        fileMenu = new FileMenu();
        editMenu = new EditMenu();
        formatMenu = new FormatMenu();

        /* Add them to the MenuBar */
        add(fileMenu);
        add(editMenu);
        add(formatMenu);
    }
}
