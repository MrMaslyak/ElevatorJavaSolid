package createrClassButtons;

import javax.swing.*;
import java.awt.*;
import UiTHred.ElevatorUI;

public class ButtonForSpeed extends JButton {
    private JButton button;
private ActionListenerButtons actionListenerButtons;
private ElevatorUI ui;

    public ButtonForSpeed(int x, int y, int width, int height, String text, ElevatorUI ui, ActionListenerButtons actionListenerButtons) {
        this.ui = ui;
        this.actionListenerButtons = actionListenerButtons;

        button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setFont(new Font("Arial", Font.ITALIC, 8));
        button.setBackground(new Color(193, 192, 192));
        button.setForeground(new Color(12, 155, 246));
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
