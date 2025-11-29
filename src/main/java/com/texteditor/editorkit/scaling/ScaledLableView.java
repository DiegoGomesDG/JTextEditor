package com.texteditor.editorkit.scaling;

/**
 * @author Stanislav Lapitsky
 * @version 1.0
 */

import javax.swing.text.Element;
import javax.swing.text.LabelView;

class ScaledLableView extends LabelView {
    GlyphPainter defaultPainter;
    public ScaledLableView(Element elem) {
        super(elem);
    }

    @Override
    protected void checkPainter() {
        if (getGlyphPainter() == null) {
            if (defaultPainter == null) {
                defaultPainter = new ScaledGlyphPainter();
            }
            setGlyphPainter(defaultPainter.getPainter(this, getStartOffset(), getEndOffset()));
        }
    }
}
