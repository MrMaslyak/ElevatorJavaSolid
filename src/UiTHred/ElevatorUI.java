package UiTHred;

import ElementsClass.ArrowPanel;
import ElementsClass.PainterFloor;
import ElementsClass.ToggleSwitch;
import createrClassButtons.ButtonForSpeed;
import createrClassButtons.MakeButtonOnFloor;
import createrClassButtons.MakeFloor;
import createrClassButtons.ActionListenerButtons;
import Interface.ElevatorUIInterface;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ElevatorUI extends JFrame implements ElevatorUIInterface {
    private int positionX = 0, positionY, floorY5 = 400, floorY4 = 475, floorY3 = 550, floorY2 = 625, floorY1 = 700;
    private JLabel liveFloor;
    private int liveFloorInt = 1, speedForElevator;
    private JPanel panelLiveFloor;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> buttonForSpeedElevator;
    private int[] floorPositions;
    private ToggleSwitch isServiceToggle;
    private boolean isService = false;
    private ArrowPanel arrowPanelUp, arrowPanelDown;
    private ActionListenerButtons actionListenerButtons;
    private PainterFloor painterFloor;

    public ElevatorUI() {
        setTitle("Elevator Simulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 800);
        setLocationRelativeTo(null);
        setLayout(null);


        ui();


        floorPositions = new int[]{floorY5, floorY4, floorY3, floorY2, floorY1};


        painterFloor = new PainterFloor(this);
        painterFloor.setBounds(0, -25, 600, 800);
        add(painterFloor);

        setVisible(true);
    }

    private void ui() {
        JLabel label = new JLabel("Elevator Simulator");
        label.setBounds(185, 25, 300, 25);
        label.setFont(new Font("Arial", Font.BOLD, 30));
        add(label);

        floorButtons = new HashMap<>();
        floorOnButtons = new HashMap<>();
        buttonForSpeedElevator = new HashMap<>();

        floorButtons.put(1, new MakeFloor(450, 100, 85, 30, "Floor 1", this, actionListenerButtons).getButton());
        floorButtons.put(2, new MakeFloor(450, 140, 85, 30, "Floor 2", this, actionListenerButtons).getButton());
        floorButtons.put(3, new MakeFloor(450, 180, 85, 30, "Floor 3", this, actionListenerButtons).getButton());
        floorButtons.put(4, new MakeFloor(450, 220, 85, 30, "Floor 4", this, actionListenerButtons).getButton());
        floorButtons.put(5, new MakeFloor(450, 260, 85, 30, "Floor 5", this, actionListenerButtons).getButton());

        panelLiveFloor = new JPanel();
        panelLiveFloor.setBounds(100, 118, 250, 150);
        panelLiveFloor.setBackground(new Color(193, 192, 192));
        add(panelLiveFloor);

        liveFloor = new JLabel();
        liveFloor.setText("Live Floor: *");
        liveFloor.setFont(new Font("Arial", Font.BOLD, 30));
        liveFloor.setBounds(23, 100, 25, 25);
        panelLiveFloor.add(liveFloor);

        floorOnButtons.put(1, new MakeButtonOnFloor(500, 685, 50, 50, "<1>", this, actionListenerButtons).getButton());
        floorOnButtons.put(2, new MakeButtonOnFloor(500, 610, 50, 50, "<2>", this, actionListenerButtons).getButton());
        floorOnButtons.put(3, new MakeButtonOnFloor(500, 535, 50, 50, "<3>", this, actionListenerButtons).getButton());
        floorOnButtons.put(4, new MakeButtonOnFloor(500, 460, 50, 50, "<4>", this, actionListenerButtons).getButton());
        floorOnButtons.put(5, new MakeButtonOnFloor(500, 385, 50, 50, "<5>", this, actionListenerButtons).getButton());

        isServiceToggle = new ToggleSwitch();
        isServiceToggle.setBounds(480, 300, 25, 15);
        add(isServiceToggle);

        JLabel labelService = new JLabel("Service");
        labelService.setBounds(468, 323, 60, 15);
        labelService.setFont(new Font("Arial", Font.ITALIC, 15));
        add(labelService);

        buttonForSpeedElevator.put(1, new ButtonForSpeed(355, 140, 85, 30, "Speed lowly", this, actionListenerButtons).getButton());
        buttonForSpeedElevator.put(2, new ButtonForSpeed(355, 180, 85, 30, "Speed Middle", this, actionListenerButtons).getButton());
        buttonForSpeedElevator.put(3, new ButtonForSpeed(355, 220, 85, 30, "Speed Fast", this, actionListenerButtons).getButton());

        arrowPanelUp = new ArrowPanel(true);
        arrowPanelUp.setBounds(15, 140, 20, 20);
        add(arrowPanelUp);

        arrowPanelDown = new ArrowPanel(false);
        arrowPanelDown.setBounds(15, 180, 20, 20);
        add(arrowPanelDown);

        actionListenerButtons = new ActionListenerButtons(liveFloorInt, speedForElevator, floorButtons, floorOnButtons, buttonForSpeedElevator, isServiceToggle, isService, arrowPanelUp, arrowPanelDown, this);
        for (JButton button : floorButtons.values()) {
            button.addActionListener(actionListenerButtons);
        }
        for (JButton button : floorOnButtons.values()) {
            button.addActionListener(actionListenerButtons);
        }
        for (JButton button : buttonForSpeedElevator.values()) {
            button.addActionListener(actionListenerButtons);
        }
        isServiceToggle.addActionListener(actionListenerButtons);
    }

    public int[] getFloorPositions() {
        return floorPositions;
    }

    public int getLiveFloorInt() {
        return liveFloorInt;
    }

    public void setLiveFloorInt(int liveFloorInt) {
        this.liveFloorInt = liveFloorInt;
    }

    public void setPositionY(int positionY) {
        this.positionY = positionY;
        painterFloor.updatePosition(positionY);
    }

    public void updateLiveFloorLabel() {
        liveFloor.setText("Live Floor: " + liveFloorInt);
    }
}
