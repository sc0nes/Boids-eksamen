import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;



public class BoidsPanel extends JPanel implements ActionListener {
    public BoidsGUI parent;
    public BoidsLogic boidslogic;
    public int pressedX , pressedY = -10;


    public BoidsPanel(BoidsGUI p) {
        parent = p;
        boidslogic = new BoidsLogic(this);
        Timer timer = new Timer(30, this);
        timer.setRepeats(true);
        timer.start();




    }
    
    public void handleMouseClick(MouseEvent e) {
    	int x = e.getX();
        int y = e.getY();
        if (x >  0&& x <parent.Height) {
        	if (y >  0&& y <parent.Height) {
        		pressedX = x;
        		pressedY = y;
        		System.out.println(pressedX+" ");
        		System.out.println(pressedY+" ");
        	}
        }
    }
    


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;



        for (int i = 0; i<boidslogic.boidsSize;i++) {
            Graphics2D tri = (Graphics2D) g2.create();
            if(boidslogic.Updateboids.get(i).type == 1) {
                g2.setColor(Color.black);
            } else if(boidslogic.Updateboids.get(i).type == 0) {
                g2.setColor(Color.cyan);
            } else g2.setColor(Color.red);
            tri.translate(boidslogic.boids.get(i).x,boidslogic.boids.get(i).y);
            tri.rotate(boidslogic.boids.get(i).angle);

            int[] xpoints = {10,-10,-10};
            int[] ypoints = {0,-6,6};

            tri.fillPolygon(xpoints,ypoints,3);
            tri.dispose();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < boidslogic.boidsSize; i++) {

            boidslogic.Updatepos(i);
        }

        boidslogic.boids = boidslogic.Updateboids;

        repaint();

    }
}
