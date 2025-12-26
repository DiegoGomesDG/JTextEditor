package com.texteditor.manager;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DocumentManager {
    private static DocumentManager instance;
    private final List<DocumentWindow> windows = new ArrayList<>();

    public DocumentManager() {}

    public static DocumentManager getInstance() {
        if (instance == null) {
            instance = new DocumentManager();
        }
        return instance;
    }

    public DocumentWindow newDocument() {
        DocumentWindow window = new DocumentWindow(this);
        windows.add(window);
        return window;
    }

    public void openDocument() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Open Document");

        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
            return;

        File file = chooser.getSelectedFile();
        openDocument(file);
    }

    public void openDocument(File file) {
        DocumentWindow window = newDocument();
        window.getController().getFileController().openDocument(file);
    }

    public void closeWindow(DocumentWindow window) {
        windows.remove(window);
        window.getView().getFrame().dispose();

        if (windows.isEmpty()) {
            System.exit(0);
        }
    }

    public List<DocumentWindow> getWindows() {
        return windows;
    }
}
