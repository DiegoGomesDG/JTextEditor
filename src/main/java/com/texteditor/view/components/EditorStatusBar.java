package com.texteditor.view.components;

import com.texteditor.view.custom.ZoomSlider;

import javax.swing.*;
import java.awt.*;

public class EditorStatusBar extends JPanel {

    private final JLabel pageLabel;
    private final JLabel wordAndCharCountLabel;

    private final ZoomSlider zoomSlider;

    private int wordCount = 0;
    private int charCount = 0;

    public EditorStatusBar() {
        setLayout(new GridBagLayout());
        setBackground(new Color(45, 45, 45));
        setOpaque(true);
        setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

        /* Constraints */
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        /* Left Side of the Status Bar */
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(45, 45, 45));
        leftPanel.setOpaque(true);
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.X_AXIS));
        leftPanel.setAlignmentY(Component.CENTER_ALIGNMENT);

        pageLabel = new JLabel("Page 1 of 1");
        wordAndCharCountLabel = new JLabel("");

        leftPanel.add(pageLabel);
        leftPanel.add(Box.createHorizontalStrut(30));
        leftPanel.add(wordAndCharCountLabel);

        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        add(leftPanel, gbc);

        /* Right Side of the Status Bar*/
        zoomSlider = new ZoomSlider();
        zoomSlider.setAlignmentY(Component.CENTER_ALIGNMENT);

        gbc.weightx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(zoomSlider, gbc);

        setCount(10, 100);
    }

    public void setCount(int wordCount, int charCount) {
        this.charCount = charCount;
        this.wordCount = wordCount;

        String info = "";
        if (wordCount == 1) {
            info += "1 word, ";
        } else {
            info += wordCount + " words, ";
        }

        if (charCount == 1) {
            info += "1 character";
        } else {
            info += charCount + " characters";
        }

        wordAndCharCountLabel.setText(info);
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharCount() {
        return charCount;
    }

}
