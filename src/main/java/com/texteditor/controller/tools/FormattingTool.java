package com.texteditor.controller.tools;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.actions.FormattingAction;
import com.texteditor.controller.actions.*;
import com.texteditor.view.TextEditorView;
import com.texteditor.view.widgets.ColorSplitButton;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

public class FormattingTool {
    private final TextEditorView view;
    private final Map<FormattingAction, Action> textEditingActions = new EnumMap<>(FormattingAction.class);

    public FormattingTool(TextEditorView view) {
        this.view = view;

        registerActions();
        setupToolBarActions();
    }

    private void registerActions() {
        /* Text Font, Size */
        textEditingActions.put(FormattingAction.TEXT_SIZE, new FontSizeAction(view.getEditorPane()));
        textEditingActions.put(FormattingAction.TEXT_FONT, new ChangeFontAction(view.getEditorPane()));

        /* Text */
        textEditingActions.put(FormattingAction.BOLD, new StyledEditorKit.BoldAction());
        textEditingActions.put(FormattingAction.ITALIC, new StyledEditorKit.ItalicAction());
        textEditingActions.put(FormattingAction.UNDERLINE, new StyledEditorKit.UnderlineAction());
        textEditingActions.put(FormattingAction.STRIKETHROUGH, new StrikeThroughAction());
        textEditingActions.put(FormattingAction.SUBSCRIPT, new SubscriptAction());
        textEditingActions.put(FormattingAction.SUPERSCRIPT, new SuperscriptAction());

        /* Clear Formatting */
        textEditingActions.put(FormattingAction.CLEAR_FORMATTING, new ClearFormattingAction());

        /* Text Color */
        textEditingActions.put(FormattingAction.TEXT_COLOR, new TextColorAction(view.getEditorPane()));
        textEditingActions.put(FormattingAction.HIGHLIGHT_COLOR, new HighlightColorAction(view.getEditorPane()));

        /* Text Alignment */
        textEditingActions.put(FormattingAction.ALIGN_LEFT, new StyledEditorKit.AlignmentAction(
            "left",
            StyleConstants.ALIGN_LEFT
        ));
        textEditingActions.put(FormattingAction.ALIGN_CENTER, new StyledEditorKit.AlignmentAction(
            "center",
            StyleConstants.ALIGN_CENTER
        ));
        textEditingActions.put(FormattingAction.ALIGN_RIGHT, new StyledEditorKit.AlignmentAction(
            "right",
            StyleConstants.ALIGN_RIGHT
        ));
        textEditingActions.put(FormattingAction.JUSTIFY, new StyledEditorKit.AlignmentAction(
            "justified",
            StyleConstants.ALIGN_JUSTIFIED
        ));
    }

    private void setupToolBarActions() {
        bindActionToComboBox(FormattingAction.TEXT_SIZE);
        bindActionToComboBox(FormattingAction.TEXT_FONT);

        bindActionToButton(FormattingAction.BOLD, "icons/toolbar/bold.svg", "Bold");
        bindActionToButton(FormattingAction.ITALIC, "icons/toolbar/italic.svg", "Italic");
        bindActionToButton(FormattingAction.UNDERLINE, "icons/toolbar/underline.svg", "Underline");
        bindActionToButton(FormattingAction.STRIKETHROUGH,  "icons/toolbar/strikethrough.svg", "Strikethrough");
        bindActionToButton(FormattingAction.SUBSCRIPT, "icons/toolbar/subscript.svg", "Subscript");
        bindActionToButton(FormattingAction.SUPERSCRIPT, "icons/toolbar/superscript.svg", "Superscript");
        bindActionToButton(FormattingAction.CLEAR_FORMATTING, "icons/toolbar/text-clear-formatting.svg", "Clear Formatting");

        /* Text Color */
        bindActionsToColorSplitButton(FormattingAction.TEXT_COLOR);

        /* Highlight Color */
        bindActionsToColorSplitButton(FormattingAction.HIGHLIGHT_COLOR);

        /* Text Alignment */
        bindActionToButton(FormattingAction.ALIGN_LEFT, "icons/toolbar/text-align/text-align-left.svg", "Align Left");
        bindActionToButton(FormattingAction.ALIGN_CENTER, "icons/toolbar/text-align/text-align-center.svg", "Align Center");
        bindActionToButton(FormattingAction.ALIGN_RIGHT, "icons/toolbar/text-align/text-align-right.svg", "Align Right");
        bindActionToButton(FormattingAction.JUSTIFY, "icons/toolbar/text-align/text-align-justify.svg", "Justify");
    }

    private void bindActionToButton(FormattingAction actionKey, String iconPath, String tooltip) {
        AbstractButton button =
            (AbstractButton) view.getEditorToolBar().getToolBarComponent(actionKey);

        Action action = textEditingActions.get(actionKey);

        button.setAction(action);
        button.setText("");
        button.setIcon(new FlatSVGIcon(iconPath));
        button.setToolTipText(tooltip);
    }

    private void bindActionsToColorSplitButton(FormattingAction actionKey) {
        ColorSplitButton button = (ColorSplitButton) view.getEditorToolBar().getToolBarComponent(actionKey);

        button.getDropButton().addActionListener(e -> {
            Color newColor = JColorChooser.showDialog(null, "Text Color", button.getColor());

            StyledColorAction action = (StyledColorAction) textEditingActions.get(actionKey);
            if (newColor != null) {
                button.setColor(newColor);

                action.setColor(newColor);
                action.actionPerformed(null); // apply to selection now
            }
        });

        button.getMainButton().addActionListener(e -> {
            Color currentColor = button.getColor();
            StyledColorAction action = (StyledColorAction) textEditingActions.get(actionKey);

            action.setColor(currentColor);   // inject the color
            action.actionPerformed(null);    // perform the action
        });
    }

    private void bindActionToComboBox(FormattingAction actionKey) {
        JComboBox<?> comboBox = (JComboBox<?>) view.getEditorToolBar().getToolBarComponent(actionKey);
        Action action = textEditingActions.get(actionKey);
        comboBox.addActionListener(action);
    }
}
