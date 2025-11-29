package com.texteditor.controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.controller.actions.*;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;
import com.texteditor.view.custom.ColorSplitButton;
import com.texteditor.view.custom.SearchDialog;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.StyledEditorKit;
import java.awt.*;
import java.util.EnumMap;
import java.util.Map;

/* Some discussions concerning separation of concerns for the MVC Architecture
* https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
*
*/
public class TextEditorController {

    private final TextDocumentModel model;
    private final TextEditorView view;

    private final Map<TextAction, Action> textEditingActions = new EnumMap<>(TextAction.class);

    public TextEditorController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;

        view.getEditorPane().setEditorKit(model.getEditorKit());
        view.getEditorPane().setDocument(model.getDocument());

        view.getEditorPane().getDocument().putProperty("ZOOM_FACTOR", 1.5);

        /* Setting up the different actions and dynamic components */
        setUpTextEditingActions();
        setUpToolBarActions();
        setUpStatusBar();

        JMenuItem findItem = view.getEditorMenuBar().getEditMenu().getFindMenuItem();
        findItem.addActionListener(e -> {
            SearchDialog searchDialog = new SearchDialog(view.getFrame());
            searchDialog.setVisible(true);
        });

        /* Trigger actino*/
        updateStatusBar(); /* Update to status bar for the new document */
    }

    private void setUpTextEditingActions() {

        /* Text Font, Size */
        textEditingActions.put(TextAction.TEXT_SIZE, new FontSizeAction(view.getEditorPane()));
        textEditingActions.put(TextAction.TEXT_FONT, new ChangeFontAction(view.getEditorPane()));

        /* Text */
        textEditingActions.put(TextAction.BOLD, new StyledEditorKit.BoldAction());
        textEditingActions.put(TextAction.ITALIC, new StyledEditorKit.ItalicAction());
        textEditingActions.put(TextAction.UNDERLINE, new StyledEditorKit.UnderlineAction());
        textEditingActions.put(TextAction.STRIKETHROUGH, new StrikeThroughAction());
        textEditingActions.put(TextAction.SUBSCRIPT, new SubscriptAction());
        textEditingActions.put(TextAction.SUPERSCRIPT, new SuperscriptAction());

        /* Clear Formatting */
        textEditingActions.put(TextAction.CLEAR_FORMATTING, new ClearFormattingAction());

        /* Text Color */
        textEditingActions.put(TextAction.TEXT_COLOR, new TextColorAction(view.getEditorPane()));
        textEditingActions.put(TextAction.HIGHLIGHT_COLOR, new HighlightColorAction(view.getEditorPane()));

        /* Text Alignment */
        textEditingActions.put(TextAction.ALIGN_LEFT, new StyledEditorKit.AlignmentAction(
            "left",
            StyleConstants.ALIGN_LEFT
        ));
        textEditingActions.put(TextAction.ALIGN_CENTER, new StyledEditorKit.AlignmentAction(
            "center",
            StyleConstants.ALIGN_CENTER
        ));
        textEditingActions.put(TextAction.ALIGN_RIGHT, new StyledEditorKit.AlignmentAction(
            "right",
            StyleConstants.ALIGN_RIGHT
        ));
        textEditingActions.put(TextAction.JUSTIFY, new StyledEditorKit.AlignmentAction(
            "justified",
            StyleConstants.ALIGN_JUSTIFIED
        ));

    }

    private void bindActionToButton(TextAction actionKey, String iconPath, String tooltip) {
        AbstractButton button =
            (AbstractButton) view.getEditorToolBar().getToolBarComponent(actionKey);

        Action action = textEditingActions.get(actionKey);

        button.setAction(action);
        button.setText("");
        button.setIcon(new FlatSVGIcon(iconPath));
        button.setToolTipText(tooltip);
    }

    /* TODO: Checking type */
    private void bindActionsToColorSplitButton(TextAction actionKey) {
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

    private void bindActionToComboBox(TextAction actionKey) {
        JComboBox<?> comboBox = (JComboBox<?>) view.getEditorToolBar().getToolBarComponent(actionKey);
        Action action = textEditingActions.get(actionKey);
        comboBox.addActionListener(action);
    }

    private void setUpToolBarActions() {
        bindActionToComboBox(TextAction.TEXT_SIZE);
        bindActionToComboBox(TextAction.TEXT_FONT);

        bindActionToButton(TextAction.BOLD, "icons/toolbar/bold.svg", "Bold");
        bindActionToButton(TextAction.ITALIC, "icons/toolbar/italic.svg", "Italic");
        bindActionToButton(TextAction.UNDERLINE, "icons/toolbar/underline.svg", "Underline");
        bindActionToButton(TextAction.STRIKETHROUGH,  "icons/toolbar/strikethrough.svg", "Strikethrough");
        bindActionToButton(TextAction.SUBSCRIPT, "icons/toolbar/subscript.svg", "Subscript");
        bindActionToButton(TextAction.SUPERSCRIPT, "icons/toolbar/superscript.svg", "Superscript");
        bindActionToButton(TextAction.CLEAR_FORMATTING, "icons/toolbar/text-clear-formatting.svg", "Clear Formatting");

        /* Text Color */
        bindActionsToColorSplitButton(TextAction.TEXT_COLOR);

        /* Highlight Color */
        bindActionsToColorSplitButton(TextAction.HIGHLIGHT_COLOR);

        /* Text Alignment */
        bindActionToButton(TextAction.ALIGN_LEFT, "icons/toolbar/text-align/text-align-left.svg", "Align Left");
        bindActionToButton(TextAction.ALIGN_CENTER, "icons/toolbar/text-align/text-align-center.svg", "Align Center");
        bindActionToButton(TextAction.ALIGN_RIGHT, "icons/toolbar/text-align/text-align-right.svg", "Align Right");
        bindActionToButton(TextAction.JUSTIFY, "icons/toolbar/text-align/text-align-justify.svg", "Justify");
    }

    private void setUpStatusBar() {
        StyledDocument document = model.getDocument();
        document.addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateStatusBar();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateStatusBar();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateStatusBar();
            }
        });
    }

    private void updateStatusBar() {
        String text = view.getEditorPane().getText();
        int charCount = text.length();

        String trimmed = text.trim();
        int wordCount = trimmed.isEmpty() ? 0 : trimmed.split("\\s+").length;

        view.getEditorStatusBar().setCount(wordCount, charCount);
    }

}
