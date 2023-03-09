package nl.tudelft.jpacman.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import javax.swing.border.AbstractBorder;

public class BottomDottedBorder extends AbstractBorder {
    private final int thickness;
    private final int length;
    private final int spacing;

    public BottomDottedBorder(int thickness, int length, int spacing) {
        this.thickness = thickness;
        this.length = length;
        this.spacing = spacing;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.setColor(Color.BLACK);
        int offset = 0;
        while (offset < width) {
            g.drawLine(offset, height - thickness, offset + length, height - thickness);
            offset += length + spacing;
        }
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(0, 0, thickness, 0);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = 0;
        insets.top = 0;
        insets.right = 0;
        insets.bottom = thickness;
        return insets;
    }
}
