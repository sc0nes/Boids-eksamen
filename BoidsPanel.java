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
    
    
    
    public void run() {
        double drawInterval = 1000000000 / 60.0; // 60 FPS in nanoseconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (boidslogic.boid != null) {
        	boidslogic.Updatepos();   // Update character positions, etc.
            repaint();  // Request a paintComponent call

            try {
                double remainingTime = (nextDrawTime - System.nanoTime()) / 1000000;
                if (remainingTime < 0) remainingTime = 0;
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
