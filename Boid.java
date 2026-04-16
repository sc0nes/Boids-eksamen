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
    x = (int)(Math.random()*500);
    y = (int)(Math.random()*500);
    speedX = (int)(Math.random()*5);
    speedY = (int)(Math.random()*5);
    }
    
    
}
