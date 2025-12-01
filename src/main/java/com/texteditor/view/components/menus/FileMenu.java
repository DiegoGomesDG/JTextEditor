package com.texteditor.view.components.menus;

import com.texteditor.actions.EditAction;
import com.texteditor.actions.FileAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.EnumMap;

public class FileMenu extends JMenu {
    private final EnumMap<FileAction, JMenuItem> menuItems = new EnumMap<>(FileAction.class);

    private final JMenuItem newDocumentMenuItem;
    private final JMenuItem openDocumentMenuItem;
    private final JMenu     openRecentMenu;

    private final JMenuItem saveMenuItem;
    private final JMenuItem saveAsMenuItem;

    private final JMenuItem propertiesMenuItem;

    public FileMenu() {
        setText("File");
        setMnemonic('F');

        /* newDocumentMenuItem */
        newDocumentMenuItem = new JMenuItem("New Document");
        newDocumentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        newDocumentMenuItem.setMnemonic('N');
        add(newDocumentMenuItem);
        menuItems.put(FileAction.NEW_DOCUMENT, newDocumentMenuItem);

        /* openDocumentMenuItem */
        openDocumentMenuItem = new JMenuItem("Open Document");
        openDocumentMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        openDocumentMenuItem.setMnemonic('O');
        add(openDocumentMenuItem);
        menuItems.put(FileAction.OPEN_DOCUMENT, openDocumentMenuItem);

        /* openRecentMenuItem */
        openRecentMenu = new JMenu("Open Recent");
        add(openRecentMenu);
        menuItems.put(FileAction.OPEN_RECENT, openRecentMenu);

        addSeparator();

        /* saveMenuItem */
        saveMenuItem = new JMenuItem("Save");
        saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        add(saveMenuItem);
        menuItems.put(FileAction.SAVE, saveMenuItem);

        /* saveAsMenuItem */
        saveAsMenuItem = new JMenuItem("Save As");
        saveAsMenuItem.setAccelerator(KeyStroke.getKeyStroke(
            KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx() | InputEvent.SHIFT_DOWN_MASK));
        add(saveAsMenuItem);
        menuItems.put(FileAction.SAVE_AS, saveAsMenuItem);

        addSeparator();

        /* propertiesMenuItem */
        propertiesMenuItem = new JMenuItem("Properties");
        add(propertiesMenuItem);
        menuItems.put(FileAction.PROPERTIES, propertiesMenuItem);
    }

    public JMenuItem getMenuItem(FileAction action) {
        return menuItems.get(action);
    }

}
