import javax.swing.*;
import java.awt.*;

public class ArrowPanel extends JPanel {
    private boolean pointingUp;
    private Color arrowColor = Color.BLACK;

    public ArrowPanel(boolean pointingUp) {
        this.pointingUp = pointingUp;
    }

    public void setArrowColor(Color color) {
        this.arrowColor = color;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Антиалиасинг для сглаживания
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(arrowColor);

        // Начальная и конечная точки стрелочки
        int x1 = getWidth() / 2;
        int y1 = pointingUp ? getHeight() * 3 / 4 : getHeight() / 4;
        int x2 = getWidth() / 2;
        int y2 = pointingUp ? getHeight() / 4 : getHeight() * 3 / 4;

        // Рисуем линию
        g2d.drawLine(x1, y1, x2, y2);

        // Длина и угол наклона стрелочных наконечников
        int arrowLength = 20;
        int arrowWidth = 10;
        double angle = Math.atan2(y2 - y1, x2 - x1);

        // Рисуем левый наконечник
        int xArrowLeft = (int) (x2 - arrowLength * Math.cos(angle - Math.PI / 6));
        int yArrowLeft = (int) (y2 - arrowLength * Math.sin(angle - Math.PI / 6));
        g2d.drawLine(x2, y2, xArrowLeft, yArrowLeft);

        // Рисуем правый наконечник
        int xArrowRight = (int) (x2 - arrowLength * Math.cos(angle + Math.PI / 6));
        int yArrowRight = (int) (y2 - arrowLength * Math.sin(angle + Math.PI / 6));
        g2d.drawLine(x2, y2, xArrowRight, yArrowRight);
    }
}
