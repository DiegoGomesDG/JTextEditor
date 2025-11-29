package com.texteditor.view.components;

import com.texteditor.model.TextDefaults;
import com.texteditor.view.widgets.SelectionCaret;

import javax.swing.*;
import java.awt.*;

public class EditorPane extends JEditorPane {

    public EditorPane() {
        setBackground(new Color(20, 20, 20));
        setFont(new Font(TextDefaults.FONT_FAMILY, Font.PLAIN, TextDefaults.FONT_SIZE));
        setCaret(new SelectionCaret());
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return true;   // forces wrapping to viewport width
    }

}
