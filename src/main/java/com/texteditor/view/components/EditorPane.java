package com.texteditor.view.components;

import com.texteditor.view.custom.SelectionCaret;

import javax.swing.*;
import java.awt.*;

public class EditorPane extends JEditorPane {

    public EditorPane() {
        setBackground(new Color(20, 20, 20));
        setFont(new Font("Times New Roman", Font.PLAIN, 16));
        setCaret(new SelectionCaret());
    }

}
