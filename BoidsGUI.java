import javax.swing.*;
import java.awt.*;

public class BoidsGUI {
    public BoidsPanel boidspanel;
    public int Width = 800;
    public int Height = 600;

    public BoidsGUI(){
        JFrame mainframe = new JFrame();
        mainframe.setVisible(true);
        mainframe.setPreferredSize(new Dimension(Width,Height));
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boidspanel = new BoidsPanel(this);
        mainframe.add(boidspanel);
        mainframe.pack();
        System.out.println("hello");

    }
}
