import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.text.AttributedCharacterIterator;
import java.util.Map;


public class BoidsPanel extends JPanel implements ActionListener {
    public BoidsGUI parent;
    public BoidsLogic boidslogic;
    double angle;


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
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);

        for (int i = 0; i<boidslogic.boidsSize;i++) {
            Graphics2D tri = (Graphics2D) g2.create();
            angle = Math.atan2(boidslogic.boids.get(i).speedY,boidslogic.boids.get(i).speedX);

            tri.translate(boidslogic.boids.get(i).x,boidslogic.boids.get(i).y);
            tri.rotate(angle);

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
