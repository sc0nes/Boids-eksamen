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
    }

    
}
