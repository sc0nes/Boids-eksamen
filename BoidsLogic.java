import java.util.*;

public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
boolean running;
public List<Boid> boids;
public List<Boid> Updateboids;
int radius;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        running = true;
        boid = new Boid(this);
        radius = 50;



        AddingBoidsToList();
        //Sepration();
        Cohrention(1);
        Alignment(1);
        
    }

    private void AddingBoidsToList() {
        boids = new ArrayList<Boid>();
        Updateboids = new ArrayList<Boid>();
        for(int i = 0; i<20;i++) {
            boids.add(new Boid(this));
        }
        for(int i = 0; i<20; i++) {
            Updateboids.add(boids.get(i));
        }
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

        Sepration(a);

        Updateboids.get(a).x += (int)Updateboids.get(a).speedX;
        Updateboids.get(a).y += (int)Updateboids.get(a).speedY;



    }

    // sepration
    public void Sepration(int n){
        int dx;
        int dy;
        double tempradius;
        double moveX = 0;
        double moveY = 0;

        for(int i = 0; i<3; i++){
            if (i != n){
                dx = boids.get(n).x - boids.get(i).x;
                dy = boids.get(n).y - boids.get(i).y;
                tempradius = Math.sqrt(dx*dx + dy*dy);
                if (tempradius < radius){
                    moveX += dx / tempradius;
                    moveY += dy / tempradius;
                }


            }
        }
        Updateboids.get(n).speedX += moveX * 0.5;
        Updateboids.get(n).speedY += moveY * 0.5;
    }

    // cohrention
    public void Cohrention(int n){
        System.out.println("det");
    }

    // alignment
    public void Alignment(int n){
        System.out.println("virker");
    }
}
