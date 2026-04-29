import java.util.*;

public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
boolean running;
public List<Boid> boids;
public List<Boid> Updateboids;
int separationRadius;
int alignmentRadius;
int cohrentionRadius;
int boidsSize;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        running = true;
        boid = new Boid(this);
        alignmentRadius = 40;
        cohrentionRadius = 60;
        separationRadius = 30;
        boidsSize = 200;

       AddingBoidsToList();
    }

    private void AddingBoidsToList() {
        boids = new ArrayList<Boid>();
        Updateboids = new ArrayList<Boid>();
        for(int i = 0; i<boidsSize ;i++) {
            boids.add(new Boid(this));
        }
        for(int i = 0; i<boidsSize; i++) {
            Updateboids.add(boids.get(i));
        }


    }

    public void Updatepos(int a) {
    	
    	if (boids.get(a).x > parent.parent.Width) {
            boids.get(a).x -= parent.parent.Width;
    	}
    	
    	if ( boids.get(a).x < 0) {
    		boids.get(a).x += parent.parent.Width;
    	}
    	
    	if ( boids.get(a).y > parent.parent.Height) {
            boids.get(a).y -= parent.parent.Height;
    	}
    	
    	if (boids.get(a).y < 0) {
            boids.get(a).y += parent.parent.Height;
    	}

        Sepration(a);
        Alignment(a);
        //Cohrention(a);

        Updateboids.get(a).x += (int)Updateboids.get(a).speedX();
        Updateboids.get(a).y += (int)Updateboids.get(a).speedY();



    }

    //sepration
    public void Sepration(int n){

        double moveX = 0;
        double moveY = 0;

        for(int i = 0; i<boidsSize; i++){
            if (i != n){
                int dx = boids.get(n).x - boids.get(i).x;
                int dy = boids.get(n).y - boids.get(i).y;
                double tempradius = Math.sqrt(dx*dx + dy*dy);
                if (tempradius < separationRadius){
                    moveX += dx / tempradius;
                    moveY += dy / tempradius;
                }


            }
        }
        Updateboids.get(n).angle += Math.atan2(moveY,moveX)*0.25;
    }

    // cohrention


    public void Cohrention(int n){
    	int sumx = 0;
    	int sumy = 0;
    	int antal = 0;

    	for(int i = 0; i<boidsSize; i++){
            if (i != n) {
                int dx = boids.get(n).x - boids.get(i).x;
                int dy = boids.get(n).y - boids.get(i).y;
                double tempradius = Math.sqrt(dx * dx + dy * dy);
                if (tempradius < cohrentionRadius) {
                	sumx += boids.get(i).x;
                	sumy += boids.get(i).y;
                	antal++;
                }
            }
        }
    	if (antal!=0) {
    		boids.get(n).angle += Math.atan2(sumy,sumx);
    	}
    	
    }

    // alignment
    public void Alignment(int n){
        double alignmentX = 0;
        double alignmentY = 0;
        int count = 0;
        for(int i = 0; i<boidsSize; i++){
            if (i != n) {
                int dx = boids.get(n).x - boids.get(i).x;
                int dy = boids.get(n).y - boids.get(i).y;
                double tempradius = Math.sqrt(dx * dx + dy * dy);
                if (tempradius < alignmentRadius) {
                    alignmentX += boids.get(i).speedX();
                    alignmentY += boids.get(i).speedY();
                    count++;
                }
            }
        }
        if(count > 0){
            alignmentX = alignmentX/count;
            alignmentY = alignmentY/count;

            Updateboids.get(n).angle += Math.atan2(alignmentY - boids.get(n).speedY(),alignmentX- boids.get(n).speedX())*0.05;
        }
    }
}
