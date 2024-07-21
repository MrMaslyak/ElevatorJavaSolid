package UiTHred;

import ElementsClass.ArrowPanel;
import ElementsClass.ToggleSwitch;
import javax.swing.*;
import java.awt.*;
import java.util.Map;
import sounds.ElevatorSound;
import sounds.*;
import Interface.ThreadElevator;


public class ElevatorMoverThread extends Thread implements ThreadElevator{
    private ElevatorUI elevatorUI;
    private int targetFloor, speedForElevator;
    private int[] floorPositions;
    private Map<Integer, JButton> floorButtons;
    private Map<Integer, JButton> floorOnButtons;
    private Map<Integer, JButton> buttonForSpeedElevator;
    private ToggleSwitch isServiceToggle;
    private ArrowPanel arrowPanelUp, arrowPanelDown;
    private ElevatorSound elevatorSound;

    public ElevatorMoverThread(ElevatorUI elevatorUI, ArrowPanel arrowPanelUp, ArrowPanel arrowPanelDown, int targetFloor, int speedForElevator, int[] floorPositions, Map<Integer, JButton> floorButtons, Map<Integer, JButton> floorOnButtons, ToggleSwitch isServiceToggle, boolean isService, Map<Integer, JButton> buttonForSpeedElevator) {
        this.elevatorUI = elevatorUI;
        this.targetFloor = targetFloor;
        this.speedForElevator = speedForElevator;
        this.floorPositions = floorPositions;
        this.floorButtons = floorButtons;
        this.floorOnButtons = floorOnButtons;
        this.isServiceToggle = isServiceToggle;
        this.buttonForSpeedElevator = buttonForSpeedElevator;
        this.arrowPanelUp = arrowPanelUp;
        this.arrowPanelDown = arrowPanelDown;
        this.elevatorSound = new ElevatorSound();
    }

    @Override
    public void run() {
        while (elevatorUI.getLiveFloorInt() != targetFloor && !isServiceToggle.isSelected()) {
            if (elevatorUI.getLiveFloorInt() < targetFloor) {
                elevatorUI.setLiveFloorInt(elevatorUI.getLiveFloorInt() + 1);
                arrowPanelUp.setArrowColor(Color.RED);
            } else if (elevatorUI.getLiveFloorInt() > targetFloor) {
                elevatorUI.setLiveFloorInt(elevatorUI.getLiveFloorInt() - 1);
                arrowPanelDown.setArrowColor(Color.RED);
            }
            updateFloorButtons();
            elevatorUI.setPositionY(floorPositions[5 - elevatorUI.getLiveFloorInt()]);
            elevatorUI.updateLiveFloorLabel();
            elevatorUI.repaint();
            try {
                Thread.sleep(speedForElevator);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (isServiceToggle.isSelected()) {
            moveToFirstFloor();
        }
        arrowPanelUp.setArrowColor(new Color(237, 235, 235));
        arrowPanelDown.setArrowColor(new Color(237, 235, 235));
    }

    public void updateFloorButtons() {
        for (int i = 1; i <= 5; i++) {
            if (elevatorUI.getLiveFloorInt() == i) {
                floorButtons.get(i).setBorder(null);
                floorOnButtons.get(i).setForeground(Color.BLUE);
                elevatorSound.playSound("sounds/floor" + i + ".wav");
            }
        }
    }

    public void moveToFirstFloor() {
        while (elevatorUI.getLiveFloorInt() > 1) {
            elevatorUI.setLiveFloorInt(elevatorUI.getLiveFloorInt() - 1);
            updateFloorButtons();
            elevatorUI.setPositionY(floorPositions[5 - elevatorUI.getLiveFloorInt()]);
            elevatorUI.updateLiveFloorLabel();
            elevatorUI.repaint();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        elevatorSound.playSound("/sounds/floor1.wav");
    }
}
