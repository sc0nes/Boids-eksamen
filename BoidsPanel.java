import javax.swing.*;
import java.awt.*;

public class BoidsPanel extends JPanel{
    public BoidsGUI parent;

    public BoidsPanel(BoidsGUI p) {
        parent = p;

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillOval(parent.parent.boidslogic.boid.x,parent.parent.boidslogic.boid.y,10,10);
    }
}
