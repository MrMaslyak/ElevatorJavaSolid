package createrClassButtons;
import UiTHred.ElevatorUI;
import javax.swing.*;
import java.awt.*;

public class MakeButtonOnFloor extends JButton {
    private JButton button;
    private ElevatorUI ui;
    private ActionListenerButtons actionListenerButtons;

    public MakeButtonOnFloor(int x, int y, int width, int height, String text, ElevatorUI ui, ActionListenerButtons actionListenerButtons) {
        this.ui = ui;
        this.actionListenerButtons = actionListenerButtons;

         button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                if (getModel().isArmed()) {
                    g.setColor(Color.LIGHT_GRAY);
                } else {
                    g.setColor(getBackground());
                }
                g.fillOval(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }

            @Override
            protected void paintBorder(Graphics g) {
                g.setColor(getForeground());
                g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
            }

            @Override
            public boolean contains(int x, int y) {
                int radius = getWidth() / 2;
                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;
                return (Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= Math.pow(radius, 2);
            }
        };
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.BOLD, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(36, 64, 244));
        button.setFocusable(false);
        button.setVisible(true);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.setActionCommand(text);
        button.addActionListener(actionListenerButtons);
        ui.add(button);
    }

    public JButton getButton() {
        return button;
    }
}
