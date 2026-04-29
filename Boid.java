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
    x = (int)(Math.random()*parent.parent.parent.Width);
    y = (int)(Math.random()*parent.parent.parent.Height);
    speedX = (Math.random()*10-5);
    speedY = (Math.random()*10-5);
    }
    
    
}
