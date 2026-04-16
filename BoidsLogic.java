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
        //Sepration();
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

    public void Updatepos(int a) {
    	
    	if (boids.get(a).speedX > 0 && boids.get(a).x > parent.parent.Width - 40) {
    		boids.get(a).speedX= -boids.get(a).speedX;
    	}
    	
    	if (boids.get(a).speedX < 0 && boids.get(a).x < 1) {
    		boids.get(a).speedX= -boids.get(a).speedX;
    	}
    	
    	if (boids.get(a).speedY > 0 && boids.get(a).y > parent.parent.Height - 50) {
    		boids.get(a).speedY = -boids.get(a).speedY;
    	}
    	
    	if (boids.get(a).speedY < 0 && boids.get(a).y < 1) {
    		boids.get(a).speedY= -boids.get(a).speedY;
    	}
    	

        boids.get(a).x += (int)boids.get(a).speedX;
        boids.get(a).y += (int)boids.get(a).speedY;





    }

    // sepration
    public void Sepration(Boid b){

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
