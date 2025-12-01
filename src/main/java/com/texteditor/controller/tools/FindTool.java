package com.texteditor.controller.tools;

import com.texteditor.actions.EditAction;
import com.texteditor.view.TextEditorView;
import com.texteditor.view.widgets.FindDialog;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Highlighter;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class FindTool {

    private record Match(int start, int end) {}

    private final TextEditorView view;
    private final JEditorPane editor;
    private final DefaultStyledDocument document;
    private final FindDialog findDialog;

    private final List<Match> results = new ArrayList<>();
    private int currentIndex = -1;

    public FindTool(TextEditorView view, DefaultStyledDocument document) {
        this.view = view;
        this.document = document;
        this.editor = view.getEditorPane();

        this.findDialog = new FindDialog(view.getFrame());

        setupActionListeners();
        setupWindowListeners();

        JMenuItem findItem = view.getEditorMenuBar().getEditMenu().getMenuItem(EditAction.FIND);
        findItem.addActionListener(e -> openDialog());
    }

    /* Dialog button listeners setter */
    private void setupActionListeners() {
        findDialog.getSearchField().addActionListener(e ->
            search(findDialog.getSearchText())
        );

        // Navigator buttons
        findDialog.getNextButton().addActionListener(e -> next());
        findDialog.getPreviousButton().addActionListener(e -> previous());
    }

    private void setupWindowListeners() {
        findDialog.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                clearHighlights();
            }

            @Override
            public void windowClosed(WindowEvent e) {
                clearHighlights();
            }
        });
    }

    /* Expose the dialog visibility to external functions */
    public void openDialog() {
        findDialog.setVisible(true);
    }

    /* Searching algorithm */
    public void search(String query) {
        results.clear();
        currentIndex = -1;

        if (query == null || query.isBlank()) {
            clearHighlights();
            return;
        }

        try {
            /* Temporary: transform everything to lower case*/
            String content = document.getText(0, document.getLength()).toLowerCase();
            query = query.toLowerCase();

            int index = content.indexOf(query);
            while (index != -1) {
                results.add(new Match(index, index + query.length()));
                index = content.indexOf(query, index + 1);
            }

            //highlightAll();

            if (!results.isEmpty()) {
                currentIndex = 0;
                selectCurrent();
            }

        } catch (BadLocationException ignored) {
            /* The caught error is just ignored and the program proceeds with the execution*/
        }
    }

    public void next() {
        if (results.isEmpty()) return;

        if (currentIndex < results.size() - 1) {
            currentIndex++;
            selectCurrent();
        }

        selectCurrent();
    }

    public void previous() {
        if (results.isEmpty()) return;

        if (currentIndex > 0) {
            currentIndex--;
            selectCurrent();
        }

        selectCurrent();
    }

    private void highlightAll() {
        Highlighter highlighter = editor.getHighlighter();
        highlighter.removeAllHighlights();

        DefaultHighlighter.DefaultHighlightPainter painter =
            new DefaultHighlighter.DefaultHighlightPainter(new Color(255, 250, 150));

        for (Match m : results) {
            try {
                highlighter.addHighlight(m.start(), m.end(), painter);
            } catch (BadLocationException ignored) {
                /* The caught error is just ignored and the program proceeds with the execution */
            }
        }
    }

    public void clearHighlights() {
        editor.getHighlighter().removeAllHighlights();
    }

    private void selectCurrent() {
        if (currentIndex < 0 || currentIndex >= results.size()) return;

        Match match = results.get(currentIndex);

        editor.select(match.start(), match.end());
    }

}
