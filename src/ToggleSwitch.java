import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleSwitch extends JToggleButton {

    private static final int BUTTON_WIDTH = 25;
    private static final int BUTTON_HEIGHT = 15;
    private static final int CIRCLE_DIAMETER = 13;

    public ToggleSwitch() {
        setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBackground(new Color(150, 150, 150));

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackground(isSelected() ? new Color(0, 150, 0) : new Color(150, 150, 150));
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw background
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, BUTTON_WIDTH, BUTTON_HEIGHT, BUTTON_HEIGHT, BUTTON_HEIGHT);

        // Draw the circle
        int circleX = isSelected() ? BUTTON_WIDTH - CIRCLE_DIAMETER - 1 : 1;
        g2.setColor(Color.WHITE);
        g2.fillOval(circleX, 1, CIRCLE_DIAMETER, CIRCLE_DIAMETER);

        g2.dispose();
    }
}