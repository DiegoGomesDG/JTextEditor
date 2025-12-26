package com.texteditor.controller.tools;

import com.texteditor.model.TextDocumentModel;
import com.texteditor.view.TextEditorView;

import javax.swing.*;

public class ZoomTool {

    private final TextEditorView view;
    private final TextDocumentModel model;

    public ZoomTool(TextEditorView view, TextDocumentModel model) {
        this.view = view;
        this.model = model;

        setupZoomListener();
    }

    private void setupZoomListener() {
        JSlider slider = view.getEditorStatusBar().getZoomSlider().getSlider();
        slider.addChangeListener(e -> applyZoom());
    }

    /* Gets the zoom factor from the slider and applies it to the view*/
    private void applyZoom() {
        double zoomFactor = view.getEditorStatusBar().getZoomSlider().getZoomFactor();

        /* Apply the given zoomFactor*/
        /* Note: apply to the document directly */
        view.getEditorPane().getDocument().putProperty("ZOOM_FACTOR", zoomFactor);

        /* Force the editor to repaint using the new zoom */
        view.getEditorPane().revalidate();
        view.getEditorPane().repaint();
    }
}

