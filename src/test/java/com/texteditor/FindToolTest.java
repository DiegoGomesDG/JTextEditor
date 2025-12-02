package com.texteditor;

import com.texteditor.controller.tools.FindTool;
import com.texteditor.view.TextEditorView;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.text.DefaultStyledDocument;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FindToolTest {
    private DefaultStyledDocument document;
    private FindTool findTool;

    @Test
    void findTwoOccurrences() throws Exception {
        document = new DefaultStyledDocument();
        document.insertString(0, "A B A", null);

        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        TextEditorView view = new TextEditorView();

        findTool = new FindTool(view, document);
        findTool.search("A");
        List<FindTool.Match> results = findTool.getResults();

        assertEquals(2, results.size());

        assertEquals(0, results.get(0).start());
        assertEquals(1, results.get(0).end());

        assertEquals(4, results.get(1).start());
        assertEquals(5, results.get(1).end());
    }

    @Test
    void findNothing() throws Exception {
        document = new DefaultStyledDocument();
        document.insertString(0, "", null);

        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        TextEditorView view = new TextEditorView();

        findTool = new FindTool(view, document);
        findTool.search("A");
        List<FindTool.Match> results = findTool.getResults();

        assertEquals(0, results.size());
    }

    @Test
    void findSameLetters() throws Exception {
        document = new DefaultStyledDocument();
        document.insertString(0, "AAA", null);

        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
        TextEditorView view = new TextEditorView();

        findTool = new FindTool(view, document);
        findTool.search("A");
        List<FindTool.Match> results = findTool.getResults();

        assertEquals(3, results.size());

        assertEquals(0, results.get(0).start());
        assertEquals(1, results.get(0).end());

        assertEquals(1, results.get(1).start());
        assertEquals(2, results.get(1).end());

        assertEquals(2, results.get(2).start());
        assertEquals(3, results.get(2).end());
    }

    @Test
    void testCaseInsensitive() throws Exception {
        document = new DefaultStyledDocument();
        document.insertString(0, "Hello hElLo", null);

        TextEditorView view = new TextEditorView();

        findTool = new FindTool(view, document);
        findTool.search("HEL");

        List<FindTool.Match> results = findTool.getResults();

        assertEquals(2, results.size());
        assertEquals(0, results.get(0).start());
        assertEquals(3, results.get(0).end());

        assertEquals(6, results.get(1).start());
        assertEquals(9, results.get(1).end());
    }

    @Test
    void testNoMatch() throws Exception {
        document = new DefaultStyledDocument();
        document.insertString(0, "ABCDEF", null);

        TextEditorView view = new TextEditorView();

        findTool = new FindTool(view, document);
        findTool.search("Z");

        assertEquals(0, findTool.getResults().size());
    }
}
