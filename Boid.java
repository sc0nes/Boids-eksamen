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
    x = (int)(Math.random()*50);
    y = (int)(Math.random()*50);
    speedX = 1;
    speedY = 1;
    }
    
    
}
