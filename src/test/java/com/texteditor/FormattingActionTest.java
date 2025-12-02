package com.texteditor;

import com.texteditor.controller.actions.*;
import com.texteditor.model.TextDefaults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.ActionEvent;

import static org.junit.jupiter.api.Assertions.*;

class FormattingActionTest {
    StyledEditorKit kit;
    DefaultStyledDocument doc;
    JEditorPane pane;

    @BeforeEach
    void setup() throws Exception {
        /* Create a mock document*/
        kit = new StyledEditorKit();
        doc = (DefaultStyledDocument) kit.createDefaultDocument();
        doc.insertString(0, "Testing", null);

        /* Create a JEditorPane */
        pane = new JEditorPane();
        pane.setEditorKit(kit);
        pane.setDocument(doc);

        pane.select(0, 7);
    }

    @Test
    void checkChangeFontAction() {
        ChangeFontAction action = new ChangeFontAction(pane);

        JComboBox<String> combo = new JComboBox<>(new String[]{"Arial"});
        combo.setSelectedItem("Arial");

        ActionEvent evt = new ActionEvent(combo, ActionEvent.ACTION_PERFORMED, "change-font");
        action.actionPerformed(evt);

        // Check result
        AttributeSet attrs = doc.getCharacterElement(0).getAttributes();
        assertEquals("Arial", StyleConstants.getFontFamily(attrs));
    }

    @Test
    void checkClearFormattingAction() {
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        StyleConstants.setBold(attrs, true);
        StyleConstants.setItalic(attrs, true);
        StyleConstants.setForeground(attrs, Color.RED);
        doc.setCharacterAttributes(0, 5, attrs, false);

        pane.select(0, 7);

        ClearFormattingAction action = new ClearFormattingAction();
        ActionEvent evt = new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "clear-formatting");
        action.actionPerformed(evt);

        AttributeSet out = doc.getCharacterElement(0).getAttributes();

        assertEquals(TextDefaults.FONT_FAMILY, StyleConstants.getFontFamily(out));
        assertEquals(TextDefaults.FONT_SIZE, StyleConstants.getFontSize(out));
        assertEquals(TextDefaults.FONT_COLOR, StyleConstants.getForeground(out));

        assertFalse(StyleConstants.isBold(out));
        assertFalse(StyleConstants.isItalic(out));
        assertFalse(StyleConstants.isUnderline(out));
        assertFalse(StyleConstants.isStrikeThrough(out));
        assertFalse(StyleConstants.isSuperscript(out));
        assertFalse(StyleConstants.isSubscript(out));
    }

    @Test
    void checkFontSizeAction() {
        FontSizeAction action = new FontSizeAction(pane);

        JComboBox<Integer> combo = new JComboBox<>(new Integer[]{14});
        combo.setSelectedItem(14);

        ActionEvent evt = new ActionEvent(combo, ActionEvent.ACTION_PERFORMED, "font-size");
        action.actionPerformed(evt);

        // Check result
        AttributeSet attrs = doc.getCharacterElement(0).getAttributes();
        assertEquals(14, StyleConstants.getFontSize(attrs));
    }

    @Test
    void TextColorAction() {
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        doc.setCharacterAttributes(0, 7, attrs, false);

        pane.select(0, 7);

        TextColorAction action = new TextColorAction(pane);
        action.setColor(Color.YELLOW);
        ActionEvent evt = new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "text-color");
        action.actionPerformed(evt);

        AttributeSet out = doc.getCharacterElement(0).getAttributes();

        assertEquals(Color.YELLOW, StyleConstants.getForeground(out));
    }

    @Test
    void HighlightColorAction() {
        SimpleAttributeSet attrs = new SimpleAttributeSet();
        doc.setCharacterAttributes(0, 7, attrs, false);

        pane.select(0, 7);

        HighlightColorAction action = new HighlightColorAction(pane);
        action.setColor(Color.YELLOW);
        ActionEvent evt = new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "highlight-text");
        action.actionPerformed(evt);

        AttributeSet out = doc.getCharacterElement(0).getAttributes();

        assertEquals(Color.YELLOW, StyleConstants.getBackground(out));
    }

    @Test
    void StrikeThroughAction() {
        StrikeThroughAction action = new StrikeThroughAction();

        ActionEvent evt =
            new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "strike");

        action.actionPerformed(evt);

        AttributeSet attrs = doc.getCharacterElement(0).getAttributes();
        assertTrue(StyleConstants.isStrikeThrough(attrs));
    }


    @Test
    void SubscriptAction() {
        SubscriptAction action = new SubscriptAction();
        ActionEvent evt =
            new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "subscript");

        action.actionPerformed(evt);

        AttributeSet attrs = doc.getCharacterElement(0).getAttributes();
        assertTrue(StyleConstants.isSubscript(attrs));
    }

    @Test
    void SuperscriptAction() {
        SuperscriptAction action = new SuperscriptAction();
        ActionEvent evt =
            new ActionEvent(pane, ActionEvent.ACTION_PERFORMED, "superscript");

        action.actionPerformed(evt);

        AttributeSet attrs = doc.getCharacterElement(0).getAttributes();
        assertTrue(StyleConstants.isSuperscript(attrs));
    }
}
