package nl.tudelft.jpacman.ui;

import java.awt.*;
import javax.swing.border.*;

public class RoundBorder extends AbstractBorder {
    private int radius;

    public RoundBorder(int radius) {
        this.radius = radius;
    }

    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(c.getBackground());
        g2.fillRoundRect(x, y, width, height, radius, radius);

        g2.setColor(c.getForeground());
        g2.drawRoundRect(x, y, width, height, radius, radius);
    }

    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
    }

    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = this.radius + 1;
        insets.top = this.radius + 1;
        insets.bottom = this.radius + 2;
        return insets;
    }
}
