package com.texteditor;

import com.texteditor.manager.DocumentManager;
import com.texteditor.manager.DocumentWindow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class DocumentManagerTest {
    @BeforeEach
    void setup() {
        UIManager.put("defaultFont", new Font("SansSerif", Font.PLAIN, 14));
    }

    @Test
    void testCreateNewDocument() {
        DocumentManager dm = new DocumentManager();

        DocumentWindow window = dm.newDocument();

        assertNotNull(window);
        assertNotNull(window.getModel());
        assertTrue(dm.getWindows().contains(window));
    }

    @Test
    void testClosingDocumentRemovesWindow() {
        DocumentManager dm = new DocumentManager();
        DocumentWindow w1 = dm.newDocument();
        DocumentWindow w2 = dm.newDocument();

        dm.closeWindow(w1);

        assertFalse(dm.getWindows().contains(w1));
        assertTrue(dm.getWindows().contains(w2));
    }
}
