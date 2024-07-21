package ElementsClass;

import javax.swing.*;
import java.awt.*;
import Interface.Arrow;

public class ArrowPanel extends JPanel implements Arrow {
    private boolean pointingUp;
    private Color arrowColor = Color.BLACK;

    public ArrowPanel(boolean pointingUp) {
        this.pointingUp = pointingUp;
    }

    @Override
    public void setArrowColor(Color color) {
        this.arrowColor = color;
        repaint();
    }

    @Override
    public void setPointingUp(boolean pointingUp) {
        this.pointingUp = pointingUp;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(arrowColor);
        drawArrow(g2d, getWidth(), getHeight());
    }

    @Override
    public void drawArrow(Graphics2D g2d, int width, int height) {
        int x1 = width / 2;
        int y1 = pointingUp ? height * 3 / 4 : height / 4;
        int x2 = width / 2;
        int y2 = pointingUp ? height / 4 : height * 3 / 4;
        g2d.drawLine(x1, y1, x2, y2);

        int arrowLength = 20;
        int arrowWidth = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);
        int xArrowLeft = (int) (x2 - arrowLength * Math.cos(angle - Math.PI / 6));
        int yArrowLeft = (int) (y2 - arrowLength * Math.sin(angle - Math.PI / 6));
        g2d.drawLine(x2, y2, xArrowLeft, yArrowLeft);
        int xArrowRight = (int) (x2 - arrowLength * Math.cos(angle + Math.PI / 6));
        int yArrowRight = (int) (y2 - arrowLength * Math.sin(angle + Math.PI / 6));
        g2d.drawLine(x2, y2, xArrowRight, yArrowRight);
    }
}
