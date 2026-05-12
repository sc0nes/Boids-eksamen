import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.*;

public class BoidsGUI extends JPanel{
	public BoidsPanel boidspanel;
    public int Width = 800;
    public int Height = 600;

        public BoidsGUI(){
        
        boidspanel = new BoidsPanel(this);
        JFrame mainframe = new JFrame();
        
        
        //Have taking this from Chess Projekt
        mainframe.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
            	boidspanel.handleMouseClick(e);
            }
        });
        
        
        mainframe.setVisible(true);
        mainframe.setPreferredSize(new Dimension(Width,Height));
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainframe.add(boidspanel);
        mainframe.pack();
    }
}
