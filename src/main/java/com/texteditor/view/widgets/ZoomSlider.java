package com.texteditor.view.widgets;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;
import java.awt.*;

public class ZoomSlider extends JPanel {
    private final JSlider slider;
    private final JButton plusButton;
    private final JButton minusButton;
    private final JLabel zoomPercentageLabel;

    private int zoomPercentage;

    public ZoomSlider() {
        setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
        setBackground(new Color(45, 45, 45));
        setOpaque(true);

        zoomPercentage = 100;

        /* Slider */
        slider = new JSlider(20, 400, zoomPercentage);
        slider.putClientProperty("JComponent.sizeVariant", "small"); // small preset
        slider.putClientProperty("JSlider.trackWidth", 4);           // thinner track
        slider.putClientProperty("JSlider.thumbSize", new Dimension(10, 10)); // smaller thumb
        slider.setPreferredSize(new Dimension(150, 24));


        /* Plus Button */
        plusButton = new JButton();
        plusButton.putClientProperty("JButton.buttonType", "toolBarButton");
        plusButton.setIcon(new FlatSVGIcon("icons/slider/plus.svg"));
        plusButton.setToolTipText("Zoom In");
        plusButton.setFocusPainted(false);


        /* Minus Button */
        minusButton = new JButton();
        minusButton.putClientProperty("JButton.buttonType", "toolBarButton");
        minusButton.setIcon(new FlatSVGIcon("icons/slider/minus.svg"));
        minusButton.setToolTipText("Zoom Out");
        minusButton.setFocusPainted(false);

        /* Percentage Label */
        zoomPercentageLabel = new JLabel(zoomPercentage + "%");

        // Reserve fixed width for up to "000%"
        FontMetrics fm = zoomPercentageLabel.getFontMetrics(zoomPercentageLabel.getFont());
        int maxWidth = fm.stringWidth("000%");
        zoomPercentageLabel.setPreferredSize(new Dimension(maxWidth, fm.getHeight()));
        zoomPercentageLabel.setHorizontalAlignment(SwingConstants.RIGHT);

        // Align visually: slight upward nudge
        JPanel sliderGroup = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        sliderGroup.setOpaque(false);
        sliderGroup.setBorder(BorderFactory.createEmptyBorder(1, 0, 0, 0)); // optical alignment tweak
        sliderGroup.add(minusButton);
        sliderGroup.add(slider);
        sliderGroup.add(plusButton);

        // Assemble in a single line
        setAlignmentY(Component.CENTER_ALIGNMENT);
        add(sliderGroup);
        add(Box.createHorizontalStrut(20));
        add(zoomPercentageLabel);

        /* Add Change Listener to the zoomPercentageLabel */
        slider.addChangeListener(e -> {
            int value = slider.getValue();
            zoomPercentageLabel.setText(value + "%");
        });

        // Add ActionListeners for buttons
        plusButton.addActionListener(e -> {
            int stepSize;
            int currentValue = slider.getValue();

            if (currentValue % 10 == 0) {
                stepSize = 10; // normal step
            } else {
                stepSize = 10 - (currentValue % 10); // snap to next multiple of 10
            }

            int newValue = slider.getValue() + stepSize;
            if (newValue <= slider.getMaximum()) {
                slider.setValue(newValue);
            }
        });

        minusButton.addActionListener(e -> {
            int stepSize;
            int currentValue = slider.getValue();

            if (currentValue % 10 == 0) {
                stepSize = 10; // normal step
            } else {
                stepSize = (currentValue % 10); // snap to next multiple of 10
            }

            int newValue = slider.getValue() - stepSize;
            if (newValue >= slider.getMinimum()) {
                slider.setValue(newValue);
            }
        });
    }

    public double getZoomFactor() {
        return slider.getValue() / 100.0;
    }

    public JSlider getSlider() {
        return slider;
    }

}
