package com.texteditor.view.components.menus;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.actions.EditAction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.EnumMap;

public class EditMenu extends JMenu {
    private final EnumMap<EditAction, JMenuItem> menuItems = new EnumMap<>(EditAction.class);

    private final JMenuItem undoMenuItem;
    private final JMenuItem redoMenuItem;

    private final JMenuItem cutMenuItem;
    private final JMenuItem copyMenuItem;
    private final JMenuItem pasteMenuItem;

    private final JMenuItem findMenuItem;

    public EditMenu() {
        setText("Edit");
        setMnemonic('E');

        /* undoMenuItem */
        undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        undoMenuItem.setMnemonic('U');
        undoMenuItem.setIcon(new FlatSVGIcon("icons/undo.svg"));
        add(undoMenuItem);
        menuItems.put(EditAction.UNDO, undoMenuItem);

        /* redoMenuItem */
        redoMenuItem = new JMenuItem("Redo");
        redoMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Y, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        redoMenuItem.setMnemonic('R');
        redoMenuItem.setIcon(new FlatSVGIcon("icons/redo.svg"));
        add(redoMenuItem);
        menuItems.put(EditAction.REDO, redoMenuItem);

        addSeparator();

        /* cutMenuItem */
        cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        cutMenuItem.setMnemonic('X');
        cutMenuItem.setIcon(new FlatSVGIcon("icons/menu-cut.svg"));
        add(cutMenuItem);
        menuItems.put(EditAction.CUT, cutMenuItem);

        /* copyMenuItem */
        copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        copyMenuItem.setMnemonic('C');
        copyMenuItem.setIcon(new FlatSVGIcon("icons/copy.svg"));
        add(copyMenuItem);
        menuItems.put(EditAction.COPY, copyMenuItem);

        /* pasteMenuItem */
        pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        pasteMenuItem.setMnemonic('P');
        pasteMenuItem.setIcon(new FlatSVGIcon("icons/menu-paste.svg"));
        add(pasteMenuItem);
        menuItems.put(EditAction.PASTE, pasteMenuItem);

        addSeparator();

        /* findMenuItem */
        findMenuItem = new JMenuItem("Find");
        findMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        findMenuItem.setMnemonic('F');
        findMenuItem.setIcon(new FlatSVGIcon("icons/search.svg"));
        add(findMenuItem);
        menuItems.put(EditAction.FIND, findMenuItem);
    }

    public JMenuItem getMenuItem(EditAction action) {
        return menuItems.get(action);
    }
}
