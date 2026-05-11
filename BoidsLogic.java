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
int huntRadius;
int clickRadius;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        running = true;
        boid = new Boid(this);
        alignmentRadius = 40;
        cohrentionRadius = 60;
        separationRadius = 30;
        boidsSize = 300;
        huntRadius = 80;
        clickRadius = 100;

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
        Boid current = boids.get(a);
    	
    	if (current.x > parent.parent.Width) {
            current.x -= parent.parent.Width;
    	}
    	
    	if ( current.x < 0) {
            current.x += parent.parent.Width;
    	}
    	
    	if ( current.y > parent.parent.Height) {
            current.y -= parent.parent.Height;
    	}
    	
    	if (current.y < 0) {
            current.y += parent.parent.Height;
    	}

        Sepration(a, current);
        Alignment(a, current);
        Cohrention(a, current);
        Movefromclick(a, current);
        if(current.type == 1) {
        	Chase(a, current);
        	
        	Updateboids.get(a).x += (int)Updateboids.get(a).speedXPredetors();
            Updateboids.get(a).y += (int)Updateboids.get(a).speedYPredetors();
        }
        if(current.type == 0) {
        	Flee(a, current);
        	
        	Updateboids.get(a).x += (int)Updateboids.get(a).speedXPrey();
            Updateboids.get(a).y += (int)Updateboids.get(a).speedYPrey();
        }
        
        



    }

    private void Flee(int n, Boid current) {

        for (int i = 0; i < boidsSize; i++) {
            Boid predator = boids.get(i);

            // prey flees predators
            if (predator.type > current.type) {

                double dx = current.x - predator.x;
                double dy = current.y - predator.y;
                double dist = Math.sqrt(dx*dx + dy*dy);

                if (dist < huntRadius) {
                    double desiredAngle = Math.atan2(dy, dx); // turn AWAY
                    turnToward(Updateboids.get(n), desiredAngle, 0.3); // flee faster

                }
            }
        }
    }
    private void Movefromclick(int n, Boid current) {
    	
    	int x = parent.pressedX;
    	int y = parent.pressedY;
    	if (!(x == -10)) {
                double dx = current.x - x;
                double dy = current.y - y;
                double dist = Math.sqrt(dx*dx + dy*dy);

                if (dist < clickRadius) {
                    double desiredAngle = Math.atan2(dy, dx); // turn AWAY
                    turnToward(Updateboids.get(n), desiredAngle, 0.5); // flee faster

                }
       }

    }


    private void Chase(int n, Boid current) {
        for (int i = 0; i<boidsSize; i++) {
            Boid target = boids.get(i);

            if (target.type < current.type) {
                // 1. Vector from current → target
                double dx = target.x - current.x;
                double dy = target.y - current.y;
                double tempraidus = Math.sqrt(dx * dx + dy * dy);
                if (tempraidus < huntRadius) {

                    double desiredAngle = Math.atan2(dy, dx);

                    turnToward(Updateboids.get(n), desiredAngle, 0.2);
                    continue;

                }
            }
        }
    }


    //sepration
    public void Sepration(int n, Boid current){

        double moveX = 0;
        double moveY = 0;

        for(int i = 0; i<boidsSize; i++){
            if (i != n){
                if(current.type == boids.get(i).type) {
                    int dx = current.x - boids.get(i).x;
                    int dy = current.y - boids.get(i).y;
                    double tempradius = Math.sqrt(dx * dx + dy * dy);
                    if (tempradius < separationRadius && tempradius != 0) {
                        moveX += dx / tempradius;
                        moveY += dy / tempradius;
                    }

                }
            }
        }
        if(moveX != 0 || moveY != 0){
            double targetAngle = Math.atan2(moveY, moveX);
            turnToward(Updateboids.get(n), targetAngle, 0.15);   // stronger turn for separation
        }
    }

    // cohrention


    public void Cohrention(int n, Boid current){


        double centerX = 0;
        double centerY = 0;
        int count = 0;

        for(int i = 0; i < boidsSize; i++){
            if(i == n) continue;

            Boid other = boids.get(i);
            if (other.type == current.type) {
                double dx = current.x - other.x;
                double dy = current.y - other.y;
                double dist = Math.sqrt(dx * dx + dy * dy);

                if (dist < cohrentionRadius) {
                    centerX += other.x;
                    centerY += other.y;
                    count++;
                }
            }
        }

        if(count > 0){
            // center of mass
            centerX /= count;
            centerY /= count;

            // direction from me → center
            double targetAngle = Math.atan2(centerY - current.y, centerX - current.x);

            // smooth turn
            turnToward(Updateboids.get(n), targetAngle, 0.03);
        }
    }


    // alignment
    public void Alignment(int n, Boid current){

        double sumX = 0;
        double sumY = 0;
        int count = 0;

        for(int i = 0; i < boidsSize; i++){
            if(i == n) continue;

            Boid other = boids.get(i);
            if (other.type == current.type){
            double dx = current.x - other.x;
            double dy = current.y - other.y;
            double dist = Math.sqrt(dx*dx + dy*dy);

            if(dist < alignmentRadius) {
                sumX += Math.cos(other.angle);
                sumY += Math.sin(other.angle);
                count++;
            }
            }
        }

        if(count > 0){
            double avgAngle = Math.atan2(sumY / count, sumX / count);
            turnToward(Updateboids.get(n), avgAngle, 0.05);
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
