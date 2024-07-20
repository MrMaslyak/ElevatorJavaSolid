package createrClassButtons;

import UiTHred.ElevatorMoverThread;
import ElementsClass.ArrowPanel;
import ElementsClass.ToggleSwitch;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;
import UiTHred.ElevatorUI;

public class ActionListenerButtons implements ActionListener {
    private int liveFloorInt = 1;
    private int speedForElevator;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> buttonForSpeedElevator;
    private ToggleSwitch isServiceToggle;
    private ElevatorUI elevatorUI;
    private ArrowPanel arrowPanelUp, arrowPanelDown;
    private boolean isService;

    public ActionListenerButtons(int liveFloorInt, int speedForElevator, Map<Integer, JButton> floorButtons,
                                 Map<Integer, JButton> floorOnButtons, Map<Integer, JButton> buttonForSpeedElevator,
                                 ToggleSwitch isServiceToggle, boolean isService, ArrowPanel arrowPanelUp, ArrowPanel arrowPanelDown, ElevatorUI elevatorUI) {
        this.liveFloorInt = liveFloorInt;
        this.speedForElevator = speedForElevator;
        this.floorButtons = floorButtons;
        this.floorOnButtons = floorOnButtons;
        this.isServiceToggle = isServiceToggle;
        this.isService = isService;
        this.elevatorUI = elevatorUI;
        this.arrowPanelUp = arrowPanelUp;
        this.arrowPanelDown = arrowPanelDown;
        this.buttonForSpeedElevator = buttonForSpeedElevator;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == isServiceToggle) {
            isService = isServiceToggle.isSelected();
        }

        int targetFloor = -1;
        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Floor 1":
            case "<1>":
                if (!isService) targetFloor = 1;
                break;
            case "Floor 2":
            case "<2>":
                if (!isService) targetFloor = 2;
                break;
            case "Floor 3":
            case "<3>":
                if (!isService) targetFloor = 3;
                break;
            case "Floor 4":
            case "<4>":
                if (!isService) targetFloor = 4;
                break;
            case "Floor 5":
            case "<5>":
                if (!isService) targetFloor = 5;
                break;
            case "Speed lowly":
                if (!isService) speedForElevator = 3000;
                break;
            case "Speed Middle":
                if (!isService) speedForElevator = 2000;
                break;
            case "Speed Fast":
                if (!isService) speedForElevator = 1000;
                break;
        }

        if (targetFloor != -1) {
            floorButtons.get(targetFloor).setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
            floorOnButtons.get(targetFloor).setForeground(Color.GREEN);

            ElevatorMoverThread moverThread = new ElevatorMoverThread(elevatorUI, arrowPanelUp, arrowPanelDown, targetFloor, speedForElevator, elevatorUI.getFloorPositions(), floorButtons, floorOnButtons, isServiceToggle, isService, buttonForSpeedElevator);
            moverThread.start();
        }
    }
}
