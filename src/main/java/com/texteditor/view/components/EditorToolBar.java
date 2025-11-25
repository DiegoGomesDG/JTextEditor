package com.texteditor.view.components;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.controller.actions.TextAction;
import com.texteditor.model.TextDefaults;
import com.texteditor.view.custom.ColorSplitButton;
import com.texteditor.view.custom.FontsComboBox;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class EditorToolBar extends JToolBar {

    private final EnumMap<TextAction, JComponent> toolBarComponents = new EnumMap<>(TextAction.class);
    private static final Color BACKGROUND_COLOR = new Color(40,40,40);

    private final JComboBox<String> paragraphStyle;
    private final JComboBox<String> fontStyles;
    private final JComboBox<Integer> fontSize;

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

        /* Add margins to create some separation between the JEditorPane and the Window Title */
        setMargin(new Insets(1, 10, 3, 10));

        /* Change background color */
        setBackground(BACKGROUND_COLOR);

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
        fontStyles.setSelectedItem(TextDefaults.FONT_FAMILY);
        toolBarComponents.put(TextAction.TEXT_FONT, fontStyles);

        /* Font Size JComboBox */
        fontSize = new JComboBox<>(new Integer[]{8, 9, 10, 11, 12, 14, 16, 18, 20, 22, 24, 26, 28, 36, 48, 72});
        fontSize.setEditable(true);
        fontSize.setSelectedItem(TextDefaults.FONT_SIZE);
        toolBarComponents.put(TextAction.TEXT_SIZE, fontSize);

        /* Bold Button */
        boldButton = new JButton();
        boldButton.setIcon(new FlatSVGIcon("icons/toolbar/bold.svg"));
        boldButton.setToolTipText("Bold");
        toolBarComponents.put(TextAction.BOLD, boldButton);

        /* Italic Button */
        italicButton = new JButton();
        italicButton.setIcon(new FlatSVGIcon("icons/toolbar/italic.svg"));
        italicButton.setToolTipText("Italic");
        toolBarComponents.put(TextAction.ITALIC, italicButton);

        /* Underline Button */
        underlineButton = new JButton();
        underlineButton.setIcon(new FlatSVGIcon("icons/toolbar/underline.svg"));
        underlineButton.setToolTipText("Underline");
        toolBarComponents.put(TextAction.UNDERLINE, underlineButton);

        /* Strikethrough Button */
        strikethroughButton = new JButton();
        strikethroughButton.setIcon(new FlatSVGIcon("icons/toolbar/strikethrough.svg"));
        strikethroughButton.setToolTipText("Strikethrough");
        toolBarComponents.put(TextAction.STRIKETHROUGH, strikethroughButton);

        /* Subscript Button */
        subscriptButton = new JButton();
        subscriptButton.setIcon(new FlatSVGIcon("icons/toolbar/subscript.svg"));
        subscriptButton.setToolTipText("Subscript");
        toolBarComponents.put(TextAction.SUBSCRIPT, subscriptButton);

        /* Superscript Button */
        superscriptButton = new JButton();
        superscriptButton.setIcon(new FlatSVGIcon("icons/toolbar/superscript.svg"));
        superscriptButton.setToolTipText("Superscript");
        toolBarComponents.put(TextAction.SUPERSCRIPT, superscriptButton);

        /* Clear Formatting Button*/
        clearFormattingButton = new JButton();
        clearFormattingButton.setIcon(new FlatSVGIcon("icons/toolbar/text-clear-formatting.svg"));
        clearFormattingButton.setToolTipText("Clear Formatting");
        toolBarComponents.put(TextAction.CLEAR_FORMATTING, clearFormattingButton);

        /* Text Color Picker Button */
        textColor = new ColorSplitButton();
        textColor.setIcon(new FlatSVGIcon("icons/toolbar/text-color.svg"));
        textColor.setToolTipText("Text Color");
        textColor.setColor(Color.RED); /* Change default color */
        toolBarComponents.put(TextAction.TEXT_COLOR, textColor);

        /* Text Highlighter Picker */
        highlightColor = new ColorSplitButton();
        highlightColor.setIcon(new FlatSVGIcon("icons/toolbar/highlight.svg"));
        highlightColor.setToolTipText("Highlight Color");
        highlightColor.setColor(Color.YELLOW); /* Change default color */
        toolBarComponents.put(TextAction.HIGHLIGHT_COLOR, highlightColor);

        /* Align Left Button */
        alignLeftButton = new JButton();
        alignLeftButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-left.svg"));
        alignLeftButton.setToolTipText("Align Left");
        toolBarComponents.put(TextAction.ALIGN_LEFT, alignLeftButton);

        /* Align Center Button */
        alignCenterButton = new JButton();
        alignCenterButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-center.svg"));
        alignCenterButton.setToolTipText("Align Center");
        toolBarComponents.put(TextAction.ALIGN_CENTER, alignCenterButton);

        /* Align Right Button */
        alignRightButton = new JButton();
        alignRightButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-right.svg"));
        alignRightButton.setToolTipText("Align Right");
        toolBarComponents.put(TextAction.ALIGN_RIGHT, alignRightButton);

        /* Justify Button */
        justifyButton = new JButton();
        justifyButton.setIcon(new FlatSVGIcon("icons/toolbar/text-align/text-align-justify.svg"));
        justifyButton.setToolTipText("Justify");
        toolBarComponents.put(TextAction.JUSTIFY, justifyButton);

        /* Create Bullet List Button */
        bulletListButton = new JButton();
        bulletListButton.setIcon(new FlatSVGIcon("icons/toolbar/bullet-list.svg"));
        bulletListButton.setToolTipText("Bullets");
        toolBarComponents.put(TextAction.BULLET_LIST, bulletListButton);

        /* Create Numbered List Button */
        numberedListButton = new JButton();
        numberedListButton.setIcon(new FlatSVGIcon("icons/toolbar/list-number.svg"));
        numberedListButton.setToolTipText("Numbering");
        toolBarComponents.put(TextAction.NUMBERED_LIST, numberedListButton);


        /* Add to the toolbar */
        add(paragraphStyle);

        addSeparator(new Dimension(20, 25));

        add(fontStyles);
        add(fontSize);

        addSeparator(new Dimension(10, 25));

        add(boldButton);
        add(italicButton);
        add(underlineButton);
        add(strikethroughButton);
        add(subscriptButton);
        add(superscriptButton);

        addSeparator(new Dimension(10, 25));

        add(clearFormattingButton);

        addSeparator(new Dimension(10, 25));

        add(textColor);
        add(highlightColor);

        addSeparator(new Dimension(10, 25));

        add(alignLeftButton);
        add(alignCenterButton);
        add(alignRightButton);
        add(justifyButton);

        addSeparator(new Dimension(10, 25));

        add(bulletListButton);
        add(numberedListButton);

    }

    public static Color getBackgroundColor() {
        return BACKGROUND_COLOR;
    }

    public Map<TextAction, JComponent> getToolBarComponents() {
        return toolBarComponents;
    }

    public JComponent getToolBarComponent(TextAction action) {
        return toolBarComponents.get(action);
    }
}
