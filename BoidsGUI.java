import javax.swing.*;
import java.awt.*;

public class BoidsGUI {
    public Handler parent;
    public BoidsPanel boidspanel;

    public BoidsGUI(Handler p){
        parent = p;
        JFrame mainframe = new JFrame();
        mainframe.setVisible(true);
        mainframe.setPreferredSize(new Dimension(800,600));
        mainframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boidspanel = new BoidsPanel(this);

        mainframe.add(boidspanel);
        mainframe.pack();

    }
}
