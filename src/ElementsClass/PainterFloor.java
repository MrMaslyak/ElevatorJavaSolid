package ElementsClass;

import Interface.DrawNew;
import UiTHred.ElevatorUI;
import Interface.PaintFloor;
import javax.swing.*;
import java.awt.*;

public class PainterFloor extends JPanel implements PaintFloor, DrawNew {
    private int positionX = 0, positionY, floorY5 = 400, floorY4 = 475, floorY3 = 550, floorY2 = 625, floorY1 = 700;
    private ElevatorUI elevatorUI;

    public PainterFloor(ElevatorUI elevatorUI) {
        this.elevatorUI = elevatorUI;
        this.positionY = elevatorUI.getFloorPositions()[5 - elevatorUI.getLiveFloorInt()];
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(100, elevatorUI.getFloorPositions()[0], 600, elevatorUI.getFloorPositions()[0]);
        g2d.drawLine(100, elevatorUI.getFloorPositions()[1], 600, elevatorUI.getFloorPositions()[1]);
        g2d.drawLine(100, elevatorUI.getFloorPositions()[2], 600, elevatorUI.getFloorPositions()[2]);
        g2d.drawLine(100, elevatorUI.getFloorPositions()[3], 600, elevatorUI.getFloorPositions()[3]);
        g2d.drawLine(100, elevatorUI.getFloorPositions()[4], 600, elevatorUI.getFloorPositions()[4]);
        g2d.drawLine(100, 775, 600, 775);
        g2d.setPaint(Color.DARK_GRAY);
        g2d.drawRect(positionX, positionY, 100, 75);
    }

    public void updatePosition(int newPositionY) {
        this.positionY = newPositionY;
        repaint();
    }

    @Override
    public void drawNewSmt() {
        repaint();
    }

}
