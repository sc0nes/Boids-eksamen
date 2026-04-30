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
        boidsSize = 50;

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
        Cohrention(a);

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
        if(moveX != 0 || moveY != 0){
            double targetAngle = Math.atan2(moveY, moveX);
            turnToward(boids.get(n), targetAngle, 0.15);   // stronger turn for separation
        }
    }

    // cohrention


    public void Cohrention(int n){
        Boid me = boids.get(n);

        double centerX = 0;
        double centerY = 0;
        int count = 0;

        for(int i = 0; i < boidsSize; i++){
            if(i == n) continue;

            Boid other = boids.get(i);

            double dx = me.x - other.x;
            double dy = me.y - other.y;
            double dist = Math.sqrt(dx*dx + dy*dy);

            if(dist < cohrentionRadius){
                centerX += other.x;
                centerY += other.y;
                count++;
            }
        }

        if(count > 0){
            // center of mass
            centerX /= count;
            centerY /= count;

            // direction from me → center
            double targetAngle = Math.atan2(centerY - me.y, centerX - me.x);

            // smooth turn
            turnToward(me, targetAngle, 0.03);
        }
    }


    // alignment
    public void Alignment(int n){

        double sumX = 0;
        double sumY = 0;
        int count = 0;

        for(int i = 0; i < boidsSize; i++){
            if(i == n) continue;

            Boid other = boids.get(i);

            double dx = boids.get(n).x - other.x;
            double dy = boids.get(n).y - other.y;
            double dist = Math.sqrt(dx*dx + dy*dy);

            if(dist < alignmentRadius){
                sumX += Math.cos(other.angle);
                sumY += Math.sin(other.angle);
                count++;
            }
        }

        if(count > 0){
            double avgAngle = Math.atan2(sumY / count, sumX / count);
            turnToward(boids.get(n), avgAngle, 0.05);
        }
    }

    private void turnToward(Boid b, double targetAngle, double turnRate){
        double diff = targetAngle - b.angle;

        // wrap to [-π, π]
        if(diff > Math.PI) diff -= 2*Math.PI;
        if(diff < -Math.PI) diff += 2*Math.PI;

        b.angle += diff * turnRate;
    }

}
