package Interface;

import java.awt.*;

public interface Arrow {
    void setArrowColor(Color color);
    void setPointingUp(boolean pointingUp);
    void drawArrow(Graphics2D g2d, int width, int height);
}
