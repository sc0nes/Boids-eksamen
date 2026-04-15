import javax.swing.*;
import java.awt.*;

public class BoidsGUI {
    public BoidsPanel boidspanel;

    public BoidsGUI(){
        JFrame mainframe = new JFrame();
        mainframe.setVisible(true);
        mainframe.setPreferredSize(new Dimension(800,600));
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boidspanel = new BoidsPanel(this);
        mainframe.add(boidspanel);
        mainframe.pack();
        System.out.println("hello");

    }
}
