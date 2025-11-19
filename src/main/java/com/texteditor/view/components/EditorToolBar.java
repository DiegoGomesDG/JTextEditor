package com.texteditor.view.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.view.custom.ColorSplitButton;
import com.texteditor.view.custom.FontsComboBox;

import javax.swing.*;
import java.awt.*;

public class EditorToolBar extends JToolBar {

    private final JComboBox<String> paragraphStyle;
    private final JComboBox<String> fontStyles;
    private final JComboBox<String> fontSize;

    private final JButton boldButton;
    private final JButton italicButton;
    private final JButton underlineButton;
    private final JButton strikethroughButton;
    private final JButton subscriptButton;
    private final JButton superscriptButton;

    private final JButton clearFormattingButton;
    private final ColorSplitButton textColor;
    private final ColorSplitButton highlightColor;

    private final JButton alignLeftButton;
    private final JButton alignCenterButton;
    private final JButton alignRightButton;
    private final JButton justifyButton;

    private final JButton bulletListButton;
    private final JButton numberedListButton;

    public EditorToolBar() {

        /* Initialize components */
        paragraphStyle = new JComboBox<>(new String[]{
            "Normal Text",
            "Title",
            "Subtitle",
            "Header 1",
            "Header 2",
            "Header 3",
            "Header 4"
        });

        /* Change to personalized */
        fontStyles = new FontsComboBox();
        fontStyles.setSelectedItem("Times New Roman");

        /* Font Size JComboBox */
        fontSize = new JComboBox<>(new String[]{"8", "9", "10", "11", "12", "14", "16", "18", "20", "24", "28", "32"});
        fontSize.setEditable(true);
        fontSize.setSelectedItem("11");

        /* Bold Button */
        boldButton = new JButton();
        boldButton.setIcon(new FlatSVGIcon("icons/toolbar/bold.svg"));
        boldButton.setToolTipText("Bold");

        /* Italic Button */
        italicButton = new JButton();
        italicButton.setIcon(new FlatSVGIcon("icons/toolbar/italic.svg"));
        italicButton.setToolTipText("Italic");

        /* Underline Button */
        underlineButton = new JButton();
        underlineButton.setIcon(new FlatSVGIcon("icons/toolbar/underline.svg"));
        underlineButton.setToolTipText("Underline");

        /* Strikethrough Button */
        strikethroughButton = new JButton();
        strikethroughButton.setIcon(new FlatSVGIcon("icons/toolbar/strikethrough.svg"));
        strikethroughButton.setToolTipText("Strikethrough");

        /* Subscript Button */
        subscriptButton = new JButton();
        subscriptButton.setIcon(new FlatSVGIcon("icons/toolbar/subscript.svg"));
        subscriptButton.setToolTipText("Subscript");

        /* Superscript Button */
        superscriptButton = new JButton();
        superscriptButton.setIcon(new FlatSVGIcon("icons/toolbar/superscript.svg"));
        superscriptButton.setToolTipText("Superscript");

        /* Clear Formatting Button*/
        clearFormattingButton = new JButton();
        clearFormattingButton.setIcon(new FlatSVGIcon("icons/toolbar/text-clear-formatting.svg"));
        clearFormattingButton.setToolTipText("Clear Formatting");

        /* Text Color Picker Button */
        textColor = new ColorSplitButton();
        textColor.setIcon(new FlatSVGIcon("icons/toolbar/text-color.svg"));
        textColor.setToolTipText("Text Color");

        /* Text Highlighter Picker */
        highlightColor = new ColorSplitButton();
        highlightColor.setIcon(new FlatSVGIcon("icons/toolbar/highlight.svg"));
        highlightColor.setToolTipText("Highlight Color");
        highlightColor.setColor(Color.YELLOW); /* Change default color */

        /* Align Left Button */
        alignLeftButton = new JButton();
        alignLeftButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-left.svg"));
        alignLeftButton.setToolTipText("Align Left");

        /* Align Center Button */
        alignCenterButton = new JButton();
        alignCenterButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-center.svg"));
        alignCenterButton.setToolTipText("Align Center");

        /* Align Right Button */
        alignRightButton = new JButton();
        alignRightButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-right.svg"));
        alignRightButton.setToolTipText("Align Right");

        /* Justify Button */
        justifyButton = new JButton();
        justifyButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-justify.svg"));
        justifyButton.setToolTipText("Justify");

        /* Create Bullet List Button */
        bulletListButton = new JButton();
        bulletListButton.setIcon(new FlatSVGIcon("icons/toolbar/bullet-list.svg"));
        bulletListButton.setToolTipText("Bullets");

        /* Create Numbered List Button */
        numberedListButton = new JButton();
        numberedListButton.setIcon(new FlatSVGIcon("icons/toolbar/list-number.svg"));
        numberedListButton.setToolTipText("Numbering");


        /* Add to the toolbar */
        add(paragraphStyle);

        addSeparator();

        add(fontStyles);
        add(fontSize);

        addSeparator();

        add(boldButton);
        add(italicButton);
        add(underlineButton);
        add(strikethroughButton);
        add(subscriptButton);
        add(superscriptButton);

        addSeparator();

        add(clearFormattingButton);
        addSeparator();
        add(textColor);
        add(highlightColor);

        addSeparator();

        add(alignLeftButton);
        add(alignCenterButton);
        add(alignRightButton);
        add(justifyButton);

        addSeparator();

        add(bulletListButton);
        add(numberedListButton);


    }
}
