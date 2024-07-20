import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ElevatorVisual extends JPanel {
    private ElevatorUI ui;
    private ElevatorController elevatorController;
    private Map<Integer, JButton> floorOnButtons;
    private int positionX = 0, positionY = 700;

    public ElevatorVisual(ElevatorUI ui, ElevatorController elevatorController) {
        this.ui = ui;
        this.elevatorController = elevatorController;
        setLayout(null);
        setBounds(175, 100, 220, 300);
        setBackground(new Color(207, 209, 208));
        ui.add(this);
        setupButtonsOnFloor();
    }

    public JButton createFloorOnButton(int x, int y, String text) {
        JButton button = new JButton(text) {
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
        button.setBounds(x, y, 50, 50);
        button.setFont(new Font("Arial", Font.BOLD, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(36, 64, 244));
        button.setFocusable(false);
        button.setContentAreaFilled(false);
        button.setFocusPainted(false);
        button.addActionListener(elevatorController);
        ui.add(button);
        return button;
    }

    public void setupButtonsOnFloor() {
        floorOnButtons = new HashMap<>();
        floorOnButtons.put(1, createFloorOnButton(5, 700, "<1>"));
        floorOnButtons.put(2, createFloorOnButton(5, 625, "<2>"));
        floorOnButtons.put(3, createFloorOnButton(5, 550, "<3>"));
        floorOnButtons.put(4, createFloorOnButton(5, 475, "<4>"));
        floorOnButtons.put(5, createFloorOnButton(5, 400, "<5>"));
    }
}
