package com.texteditor.controller;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.texteditor.controller.actions.StrikeThroughAction;
import com.texteditor.controller.actions.SubscriptAction;
import com.texteditor.controller.actions.SuperscriptAction;
import com.texteditor.controller.actions.TextAction;
import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledEditorKit;
import java.util.EnumMap;
import java.util.Map;

/* Some discussions concerning separation of concerns for the MVC Architecture
* https://stackoverflow.com/questions/5217611/the-mvc-pattern-and-swing
*
*/
public class TextEditorController {

    private TextDocumentModel model;
    private TextEditorView view;

    private final Map<TextAction, Action> textEditingActions = new EnumMap<>(TextAction.class);

    public TextEditorController(TextDocumentModel model, TextEditorView view) {
        this.model = model;
        this.view = view;

        view.getEditorPane().setDocument(model.getDocument());
        view.getEditorPane().setEditorKit(model.getEditorKit());

        setUpTextEditingActions();
        setUpToolBarActions();
    }

    private void setUpTextEditingActions() {

        /* Text Font, Size */


        /* Text */
        textEditingActions.put(TextAction.BOLD, new StyledEditorKit.BoldAction());
        textEditingActions.put(TextAction.ITALIC, new StyledEditorKit.ItalicAction());
        textEditingActions.put(TextAction.UNDERLINE, new StyledEditorKit.UnderlineAction());
        textEditingActions.put(TextAction.STRIKETHROUGH, new StrikeThroughAction());
        textEditingActions.put(TextAction.SUBSCRIPT, new SubscriptAction());
        textEditingActions.put(TextAction.SUPERSCRIPT, new SuperscriptAction());


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

    private void bindActionToButton(AbstractButton button, Action action, Icon icon, String tooltip) {
        /* Action resets the Text, Icon, ToolTip */
        button.setAction(action);

        /* Recreate the button */
        button.setText("");
        button.setIcon(icon);
        button.setToolTipText(tooltip);

    }

    private void setUpToolBarActions() {
        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.BOLD),
            textEditingActions.get(TextAction.BOLD),
            new FlatSVGIcon("icons/toolbar/bold.svg"),
            "Bold"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.ITALIC),
            textEditingActions.get(TextAction.ITALIC),
            new FlatSVGIcon("icons/toolbar/italic.svg"),
            "Italic"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.UNDERLINE),
            textEditingActions.get(TextAction.UNDERLINE),
            new FlatSVGIcon("icons/toolbar/underline.svg"),
            "Underline"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.STRIKETHROUGH),
            textEditingActions.get(TextAction.STRIKETHROUGH),
            new FlatSVGIcon("icons/toolbar/strikethrough.svg"),
            "Strikethrough"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.SUBSCRIPT),
            textEditingActions.get(TextAction.SUBSCRIPT),
            new FlatSVGIcon("icons/toolbar/subscript.svg"),
            "Subscript"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.SUPERSCRIPT),
            textEditingActions.get(TextAction.SUPERSCRIPT),
            new FlatSVGIcon("icons/toolbar/superscript.svg"),
            "Superscript"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.ALIGN_LEFT),
            textEditingActions.get(TextAction.ALIGN_LEFT),
            new FlatSVGIcon("icons/toolbar/text-align/text-align-left.svg"),
            "Align Left"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.ALIGN_CENTER),
            textEditingActions.get(TextAction.ALIGN_CENTER),
            new FlatSVGIcon("icons/toolbar/text-align/text-align-center.svg"),
            "Align Center"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.ALIGN_RIGHT),
            textEditingActions.get(TextAction.ALIGN_RIGHT),
            new FlatSVGIcon("icons/toolbar/text-align/text-align-right.svg"),
            "Align Right"
        );

        bindActionToButton(
            (AbstractButton) view.getTextEditorToolBar().getToolBarComponent(TextAction.JUSTIFY),
            textEditingActions.get(TextAction.JUSTIFY),
            new FlatSVGIcon("icons/toolbar/text-align/text-align-justify.svg"),
            "Justify"
        );

    }

}
