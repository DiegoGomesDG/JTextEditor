package com.texteditor.view.custom;

import javax.swing.*;
import java.awt.*;

/* Sources:
* https://www.tutorialspoint.com/how-to-display-the-different-font-items-inside-a-jcombobox-in-java
* https://docs.oracle.com/javase/tutorial/uiswing/components/combobox.html#renderer
* https://stackoverflow.com/questions/28073342/different-font-for-combo-box-in-java
* https://stackoverflow.com/questions/16461454/custom-font-for-jcombobox
* */

public class FontsComboBox extends JComboBox<String> {

    private final String[] fontNames;

    public FontsComboBox() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        fontNames = ge.getAvailableFontFamilyNames();

        // Add font names directly to JComboBox
        for (String font : fontNames) {
            addItem(font);
        }

        setRenderer(new FontsComboBoxRenderer());
    }

    public class FontsComboBoxRenderer extends JLabel implements ListCellRenderer<String> {

        public FontsComboBoxRenderer() {
            setOpaque(true);
            setVerticalAlignment(SwingConstants.CENTER);
            setHorizontalAlignment(SwingConstants.LEFT);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index, boolean isSelected, boolean cellHasFocus) {
            // Text to show
            setText(value);

            // Get the default font
            Font defaultFont = UIManager.getFont("defaultFont");
            int size = defaultFont.getSize();

            // Show each item in its font
            setFont(new Font(value, Font.PLAIN, size));

            // Highlight selection
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }

            return this;
        }
    }

}
