package com.texteditor.view.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.SystemInfo;

import javax.swing.*;
import java.awt.*;

public class TitleBarToolBar extends JPanel {

    private final JPanel macFullWindowContentButtonsPlaceholder;

    private final JToolBar toolBar;
    private final JButton redoButton;
    private final JButton undoButton;

    private final JButton cutButton;
    private final JButton copyButton;
    private final JButton pasteButton;

    private final JLabel titleLabel;


    public TitleBarToolBar() {

        /* Add the Spacing for the Window Buttons */
        setLayout(new BorderLayout());

        macFullWindowContentButtonsPlaceholder = new JPanel();
        macFullWindowContentButtonsPlaceholder.setLayout(new FlowLayout());
        macFullWindowContentButtonsPlaceholder.putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT_BUTTONS_PLACEHOLDER, "mac zeroInFullScreen");

        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(macFullWindowContentButtonsPlaceholder, BorderLayout.WEST);

        {
            /* ToolBar */
            toolBar = new JToolBar();

            /* redoButton */
            redoButton = new JButton();
            redoButton.setToolTipText("Redo");
            redoButton.setIcon(new FlatSVGIcon("icons/redo.svg"));

            /* undoButton */
            undoButton = new JButton();
            undoButton.setToolTipText("Undo");
            undoButton.setIcon(new FlatSVGIcon("icons/undo.svg"));

            /* cutButton */
            cutButton = new JButton("");
            cutButton.setToolTipText("Cut");
            cutButton.setIcon(new FlatSVGIcon("icons/menu-cut.svg"));

            /* copyButton */
            copyButton = new JButton();
            copyButton.setToolTipText("Copy");
            copyButton.setIcon(new FlatSVGIcon("icons/copy.svg"));

            /* pasteButton */
            pasteButton = new JButton();
            pasteButton.setToolTipText("Paste");
            pasteButton.setIcon(new FlatSVGIcon("icons/menu-paste.svg"));

            /* Add to the Left Panel */
            toolBar.add(undoButton);
            toolBar.add(redoButton);
            toolBar.addSeparator();
            toolBar.add(cutButton);
            toolBar.add(copyButton);
            toolBar.add(pasteButton);

        }

        leftPanel.add(toolBar, BorderLayout.CENTER);
        add(leftPanel, BorderLayout.WEST);

        /* Center Panel (Document Name) */
        titleLabel = new JLabel("Untitled Document", SwingConstants.CENTER);
        titleLabel.setOpaque(false);

        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);
        centerPanel.add(titleLabel, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        /* Right Panel */
        JPanel rightPanel = new JPanel();
        rightPanel.setOpaque(false);

        // match leftPanel width
        rightPanel.setPreferredSize(leftPanel.getPreferredSize());
        rightPanel.setMinimumSize(leftPanel.getPreferredSize());
        rightPanel.setMaximumSize(leftPanel.getPreferredSize());

        add(rightPanel, BorderLayout.EAST);

        int titleBarHeight = 32; // macOS-style height
        setPreferredSize(new Dimension(1, titleBarHeight));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, titleBarHeight));

    }

}
