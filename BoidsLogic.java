import java.util.*;

public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
boolean running;
public List<Boid> boids;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        running = true;
        boid = new Boid(this);



        AddingBoidsToList();
        Sepration();
        Cohrention();
        Alignment();
        
    }

    private void AddingBoidsToList() {
        boids = new ArrayList<Boid>(100);

        boids.add(new Boid(this));
        boids.add(new Boid(this));
        boids.add(new Boid(this));
        System.out.println(boids);
    }

    public void Updatepos() {

        boid.x=(int)(boid.x+boid.speedX);
        boid.y=(int)(boid.y+boid.speedY);
        parent.repaint();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }


    }

    // sepration
    public void Sepration(){

    }

    // cohrention
    public void Cohrention(){
        System.out.println("det");
    }

    // alignment
    public void Alignment(){
        System.out.println("virker");
    }
}
