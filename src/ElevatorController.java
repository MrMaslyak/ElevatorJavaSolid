import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class ElevatorController implements ActionListener {
    private ElevatorUI ui;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> speedButtons;
    private Map<Integer, JButton> floorOnButtons;
    private ArrowPanel arrowUp, arrowDown;
    private ToggleSwitch serviceToggle;
    private int[] floorPositions = {400, 475, 550, 625, 700};
    private boolean isService = false;
    private int speedForElevator = 2000;

    public ElevatorController(ElevatorUI ui) {
        this.ui = ui;
        floorButtons = new HashMap<>();
        speedButtons = new HashMap<>();
        floorOnButtons = new HashMap<>();
    }

    public void setupButtons() {
        floorButtons.put(1, createFloorButton(450, 100, "Floor 1"));
        floorButtons.put(2, createFloorButton(450, 140, "Floor 2"));
        floorButtons.put(3, createFloorButton(450, 180, "Floor 3"));
        floorButtons.put(4, createFloorButton(450, 220, "Floor 4"));
        floorButtons.put(5, createFloorButton(450, 260, "Floor 5"));
    }

    public void setupButtonsOnFloor() {
        floorOnButtons.put(1, createFloorOnButton(5, 700, "<1>"));
        floorOnButtons.put(2, createFloorOnButton(5, 625, "<2>"));
        floorOnButtons.put(3, createFloorOnButton(5, 550, "<3>"));
        floorOnButtons.put(4, createFloorOnButton(5, 475, "<4>"));
        floorOnButtons.put(5, createFloorOnButton(5, 400, "<5>"));
    }

    public void setupToggleSwitch() {
        serviceToggle = new ToggleSwitch();
        serviceToggle.setBounds(480, 300, 25, 15);
        serviceToggle.addActionListener(this);
        ui.add(serviceToggle);

        JLabel labelService = new JLabel("Service");
        labelService.setBounds(468, 323, 60, 15);
        labelService.setFont(new Font("Arial", Font.ITALIC, 15));
        ui.add(labelService);
    }

    public void setupSpeedButtons() {
        speedButtons.put(1, createSpeedButton(355, 140, "Speed Low"));
        speedButtons.put(2, createSpeedButton(355, 180, "Speed Medium"));
        speedButtons.put(3, createSpeedButton(355, 220, "Speed Fast"));
    }

    public void setupArrowPanels() {
        arrowUp = new ArrowPanel(true);
        arrowUp.setBounds(15, 140, 35, 80);
        arrowUp.setBackground(new Color(237, 235, 235));
        ui.add(arrowUp);

        arrowDown = new ArrowPanel(false);
        arrowDown.setBounds(50, 140, 35, 80);
        arrowDown.setBackground(new Color(237, 235, 235));
        ui.add(arrowDown);
    }

    private JButton createFloorButton(int x, int y, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 85, 30);
        button.setFont(new Font("Arial", Font.ITALIC, 15));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(244, 60, 36));
        button.setFocusable(false);
        button.addActionListener(this);
        ui.add(button);
        return button;
    }

    private JButton createSpeedButton(int x, int y, String text) {
        JButton button = new JButton(text);
        button.setBounds(x, y, 85, 30);
        button.setFont(new Font("Arial", Font.ITALIC, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(12, 155, 246));
        button.setFocusable(false);
        button.addActionListener(this);
        ui.add(button);
        return button;
    }

    private JButton createFloorOnButton(int x, int y, String text) {
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
        button.addActionListener(this);
        ui.add(button);
        return button;
    }

    public void actionPerformed(ActionEvent e) {
        int targetFloor = 1;
        switch (e.getActionCommand()) {
            case "Floor 1":
            case "<1>":
                if (!isService) {
                    targetFloor = 1;
                    updateButtonColors(1);
                }
                break;
            case "Floor 2":
            case "<2>":
                if (!isService) {
                    targetFloor = 2;
                    updateButtonColors(2);
                }
                break;
            case "Floor 3":
            case "<3>":
                if (!isService) {
                    targetFloor = 3;
                    updateButtonColors(3);
                }
                break;
            case "Floor 4":
            case "<4>":
                if (!isService) {
                    targetFloor = 4;
                    updateButtonColors(4);
                }
                break;
            case "Floor 5":
            case "<5>":
                if (!isService) {
                    targetFloor = 5;
                    updateButtonColors(5);
                }
                break;
            case "Speed Low":
                if (!isService) {
                    speedForElevator = 3000;
                }
                break;
            case "Speed Medium":
                if (!isService) {
                    speedForElevator = 2000;
                }
                break;
            case "Speed Fast":
                if (!isService) {
                    speedForElevator = 1000;
                }
                break;
        }

        if (e.getSource() == serviceToggle) {
            isService = serviceToggle.isSelected();
        }
    }

    private void updateButtonColors(int floor) {
        floorButtons.values().forEach(button -> button.setBorder(null));
        floorOnButtons.values().forEach(button -> button.setForeground(Color.BLACK));

        if (floorButtons.containsKey(floor)) {
            floorButtons.get(floor).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
        }
        if (floorOnButtons.containsKey(floor)) {
            floorOnButtons.get(floor).setForeground(Color.GREEN);
        }
    }
}
