package com.texteditor.view.widgets;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.icons.FlatSearchIcon;

import javax.swing.*;
import java.awt.*;

public class FindDialog extends JDialog {

    private JButton nextButton;
    private JButton previousButton;
    private JTextField searchField;

    public FindDialog(Frame frame) {
        /* Initialize JDialog */
        super(frame, "Find", false);

        /* Change windows properties */
        this.getRootPane().putClientProperty("apple.awt.fullscreenable", false);
        setResizable(false);

        /* Initialize components */
        searchField = new JTextField(20);
        nextButton = new JButton("");
        previousButton = new JButton("");

        /* FlatLaF TextField Styling */
        searchField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Search");
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSearchIcon());
        searchField.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);

        /* Style Buttons */
        previousButton.setToolTipText("Previous");
        previousButton.setIcon(new FlatSVGIcon("icons/back.svg"));

        nextButton.setToolTipText("Next");
        nextButton.setIcon(new FlatSVGIcon("icons/forward.svg"));

        /* Layout */
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));

        JToolBar buttons = new JToolBar();
        buttons.add(previousButton);
        buttons.add(nextButton);

        panel.add(searchField, BorderLayout.CENTER);
        panel.add(buttons, BorderLayout.EAST);

        setContentPane(panel);

        pack();
        setLocationRelativeTo(frame);
    }

    public JTextField getSearchField() {
        return searchField;
    }

    public String getSearchText() {
        return searchField.getText();
    }

    public JButton getPreviousButton() {
        return previousButton;
    }

    public JButton getNextButton() {
        return nextButton;
    }
}
