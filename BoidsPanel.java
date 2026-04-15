import javax.swing.*;
import java.awt.*;

public class BoidsPanel extends JPanel{
    private BoidsGUI parent;

    public BoidsPanel(BoidsGUI p) {
        parent = p;

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
}
