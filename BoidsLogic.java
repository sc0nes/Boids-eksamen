import java.util.*;

public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
boolean running;
public List<Boid> boids;
public List<Boid> Updateboids;
int separationradius;
int alignmentradius;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        running = true;
        boid = new Boid(this);
        separationradius = 50;
        alignmentradius = 75;



        AddingBoidsToList();
        Cohrention(1);

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

    }

    public void Updatepos(int a) {
    	
    	if (boids.get(a).speedX > 0 && boids.get(a).x > parent.parent.Width - 40) {
            boids.get(a).x -= parent.parent.Width;
    	}
    	
    	if (boids.get(a).speedX < 0 && boids.get(a).x < 1) {
    		boids.get(a).x += parent.parent.Width;
    	}
    	
    	if (boids.get(a).speedY > 0 && boids.get(a).y > parent.parent.Height - 50) {
            boids.get(a).y -= parent.parent.Height;
    	}
    	
    	if (boids.get(a).speedY < 0 && boids.get(a).y < 1) {
            boids.get(a).y += parent.parent.Height;
    	}

        Sepration(a);
        Alignment(a);

        Updateboids.get(a).x += (int)Updateboids.get(a).speedX;
        Updateboids.get(a).y += (int)Updateboids.get(a).speedY;



    }

    // sepration
    public void Sepration(int n){

        double moveX = 0;
        double moveY = 0;

        for(int i = 0; i<20; i++){
            if (i != n){
                int dx = boids.get(n).x - boids.get(i).x;
                int dy = boids.get(n).y - boids.get(i).y;
                double tempradius = Math.sqrt(dx*dx + dy*dy);
                if (tempradius < separationradius){
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

    }

    // alignment
    public void Alignment(int n){
        double alignmentX = 0;
        double alignmentY = 0;
        int count = 0;
        for(int i = 0; i<20; i++){
            if (i != n) {
                int dx = boids.get(n).x - boids.get(i).x;
                int dy = boids.get(n).y - boids.get(i).y;
                double tempradius = Math.sqrt(dx * dx + dy * dy);
                if (tempradius < alignmentradius) {
                    alignmentX += boids.get(i).speedX;
                    alignmentY += boids.get(i).speedY;
                    count++;
                }
            }
        }
        if(count > 0){
            alignmentX = alignmentX/count;
            alignmentY = alignmentY/count;

            Updateboids.get(n).speedX += (alignmentX-boids.get(n).speedX)*0.10;
            Updateboids.get(n).speedY += (alignmentY-boids.get(n).speedY)*0.10;
        }
    }
}
