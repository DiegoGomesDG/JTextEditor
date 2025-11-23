package com.texteditor.view.custom;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.view.components.EditorToolBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/* https://stackoverflow.com/questions/40769190/how-can-i-highlight-jpanels-on-mouse-hover */
public class ColorSplitButton extends JPanel {
    private final JButton mainButton;
    private final JButton dropButton;
    FlatSVGIcon icon;
    Color currentColor;
    private boolean hover = false;

    public ColorSplitButton() {
        setLayout(new GridLayout(1, 2));
        setOpaque(true);
        setBackground(EditorToolBar.getBackgroundColor());
        setMaximumSize(new Dimension(40, 30));

        /* Main Button */
        mainButton = new JButton();
        mainButton.putClientProperty("JButton.buttonType", "toolBarButton");
        mainButton.setMargin(new Insets(0, 0, 0, 0));
        mainButton.setFocusPainted(false);

        /* Drop Button */
        dropButton = new JButton();
        dropButton.putClientProperty("JButton.buttonType", "toolBarButton");
        dropButton.setIcon(UIManager.getIcon("Tree.expandedIcon"));
        dropButton.setMargin(new Insets(0, 0, 0, 0));
        dropButton.setFocusPainted(false);

        add(mainButton, BorderLayout.CENTER);
        add(dropButton, BorderLayout.EAST);

        // Shared hover logic for both buttons
        MouseAdapter hoverListener = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // check if the mouse left the entire component
                Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), ColorSplitButton.this);
                if (!new Rectangle(getSize()).contains(p)) {
                    hover = false;
                    repaint();
                }
            }
        };

        mainButton.addMouseListener(hoverListener);
        dropButton.addMouseListener(hoverListener);
    }

    public void setIcon(FlatSVGIcon icon) {
        this.icon = icon;
        mainButton.setIcon(icon);
    }

    @Override
    public void setToolTipText(String text) {
        mainButton.setToolTipText(text);
        dropButton.setToolTipText(text);
    }

    public void setColor(Color newColor) {
        currentColor = newColor;
        // Replace all fills with the given color
        // Only affects parts that were #ff0000 in the SVG
        icon.setColorFilter(new FlatSVGIcon.ColorFilter(c -> {
            if (isRed(c)) { // identify the red bar
                return newColor;
            }
            return c; // keep everything else as-is
        }));
    }

    private boolean isRed(Color c) {
        return c.getRed() > 200 && c.getGreen() < 20 && c.getBlue() < 20; // detect similar to #ff0000
    }

    public Color getColor() {
        return currentColor;
    }

    public JButton getMainButton() {
        return mainButton;
    }

    public JButton getDropButton() {
        return dropButton;
    }

    /* Allow to highlight the whole panel when one of the buttons is highlighted*/
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (hover) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Get FlatLaf hover color or fallback
            Color hoverColor = UIManager.getColor("Button.hoverBackground");
            if (hoverColor == null) hoverColor = new Color(230, 230, 230);

            // Draw unified hover background
            g2.setColor(new Color(hoverColor.getRed(), hoverColor.getGreen(), hoverColor.getBlue(), 50));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), 6, 6);

            g2.dispose();
        }
    }
}
