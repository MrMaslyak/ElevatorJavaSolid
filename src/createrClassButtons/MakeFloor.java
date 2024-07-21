package createrClassButtons;

import UiTHred.ElevatorUI;
import Interface.GetButtonForCreater;
import javax.swing.*;
import java.awt.*;

public class MakeFloor extends JButton implements GetButtonForCreater{
    private JButton button;
    private ElevatorUI ui;
    private ActionListenerButtons actionListenerButtons;

    public MakeFloor(int x, int y, int width, int height, String text, ElevatorUI ui, ActionListenerButtons actionListenerButtons) {
        this.ui = ui;
        this.actionListenerButtons = actionListenerButtons;


        button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.ITALIC, 15));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(244, 60, 36));
        button.setFocusable(false);
        button.setVisible(true);
        button.setActionCommand(text);
        button.addActionListener(actionListenerButtons);
        ui.add(button);

    }

    public JButton getButton() {
        return button;
    }
}
