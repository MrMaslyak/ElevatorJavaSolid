import javax.swing.*;
import java.awt.*;

public class ElevatorUI extends JFrame {
    private JLabel liveFloorLabel;
    private ElevatorController controller;
    private ElevatorVisual elevatorVisual;
    private int currentFloor = 1;

    public ElevatorUI() {
        setTitle("Elevator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setupUI();
        setVisible(true);
    }

    private void setupUI() {
        JLabel titleLabel = new JLabel("Elevator Simulator");
        titleLabel.setBounds(185, 25, 300, 25);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        add(titleLabel);

        liveFloorLabel = new JLabel("Live Floor: " + currentFloor);
        liveFloorLabel.setFont(new Font("Arial", Font.BOLD, 30));
        liveFloorLabel.setBounds(23, 100, 250, 25);
        add(liveFloorLabel);

        controller = new ElevatorController(this);
        elevatorVisual = new ElevatorVisual(this, controller);

        controller.setupButtons();
        controller.setupButtonsOnFloor();
        controller.setupToggleSwitch();
        controller.setupSpeedButtons();
        controller.setupArrowPanels();
    }

    public void updateLiveFloorLabel(int floor) {
        currentFloor = floor;
        liveFloorLabel.setText("Live Floor: " + currentFloor);
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(100, 700, getWidth(), 700);
        g2d.drawLine(100, 625, getWidth(), 625);
        g2d.drawLine(100, 550, getWidth(), 550);
        g2d.drawLine(100, 475, getWidth(), 475);
        g2d.drawLine(100, 400, getWidth(), 400);
        g2d.drawLine(100, 775, getWidth(), 775);
        g2d.setPaint(Color.DARK_GRAY);
        g2d.drawRect(0, 700, 100, 75);
    }
}
