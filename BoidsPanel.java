import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class BoidsPanel extends JPanel implements ActionListener {
    public BoidsGUI parent;
    public BoidsLogic boidslogic;

    public BoidsPanel(BoidsGUI p) {
        parent = p;
        boidslogic = new BoidsLogic(this);
        Timer timer = new Timer(20, this);
        timer.setRepeats(true);
        timer.start();



    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        for (int i = 0; i < 20; i++) {
            g.fillOval(boidslogic.boids.get(i).x, boidslogic.boids.get(i).y, 10, 10);
        }

    }




    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 20; i++) {
            boidslogic.Updatepos(i);
        }
        boidslogic.boids = boidslogic.Updateboids;
        repaint();

    }
}
