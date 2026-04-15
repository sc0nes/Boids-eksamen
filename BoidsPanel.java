import javax.swing.*;
import java.awt.*;

public class BoidsPanel extends JPanel{
    public BoidsGUI parent;
    public BoidsLogic boidslogic;

    public BoidsPanel(BoidsGUI p) {
        parent = p;
        boidslogic = new BoidsLogic(this);


    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.cyan);
        g.fillOval(boidslogic.boid.x,boidslogic.boid.y,10,10);
        
    }
    
}
