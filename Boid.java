import java.time.Duration;
import java.util.Vector;

public class Boid {
    private BoidsLogic parent;
    int x;
    int y;
    double speedX;
    double speedY;

    public Boid(BoidsLogic p){
    parent = p;
    int x = 10;
    int y = 10;
    speedX = 5;
    speedY = 5;

    Updatepos(x,y);

    }

    private void Updatepos(int x, int y) {

        x=(int)(x+speedX);
        y=(int)(y+speedY);
        if(!(parent.parent.boidsGUI == null)) {
            parent.parent.boidsGUI.boidspanel.repaint();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // bevar interrupt-status
        }
        Updatepos(x,y);
    }
}
