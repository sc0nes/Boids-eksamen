import java.util.*;

public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
public List<Boid> boids;
public List<Boid> Updateboids;
int separationRadius;
int alignmentRadius;
int cohrentionRadius;
int boidsSize;
int huntRadius;
int clickRadius;
int NumbersOfPredetors;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        boid = new Boid(this);
        alignmentRadius = 40;
        cohrentionRadius = 60;
        separationRadius = 30;
        boidsSize = 300;
        huntRadius = 80;
        clickRadius = 100;
        NumbersOfPredetors = (int)(boidsSize*0.05);

       AddingBoidsToList();
    }

    private void AddingBoidsToList() {
        boids = new ArrayList<>();
        Updateboids = new ArrayList<>();
        for(int i = 0; i<boidsSize ;i++) {
            boids.add(new Boid(this));
        }
        for(int i = 1; i<NumbersOfPredetors; i++){
            boids.get(i).type = 1;
        }
        Updateboids = boids;


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
        Movefromclick(a, current);

            for(int i = 0; i< boidsSize; i++) {
                if (boids.get(a).type != boids.get(i).type) continue; // floking by type
                Sepration(a, current, i);
                Alignment(a, current, i);
                Cohrention(a, current, i);
            }


        if (current.type == 1) { // predetors
            Chase(a, current);

            Updateboids.get(a).x += (int) Updateboids.get(a).speedXPredetors();
            Updateboids.get(a).y += (int) Updateboids.get(a).speedYPredetors();
        }
        if (current.type == 0) { //preys
            Flee(a, current);

            Updateboids.get(a).x += (int) Updateboids.get(a).speedXPrey();
            Updateboids.get(a).y += (int) Updateboids.get(a).speedYPrey();
        }
    }

    private void Flee(int n, Boid current) {
        for(int i = 0; i<boidsSize; i++) {
            if (i != n) {
                if (current.type < boids.get(i).type) {
                    int dx = current.x - boids.get(i).x;
                    int dy = current.y - boids.get(i).y;
                    double tempradius = Math.sqrt(dx * dx + dy * dy);
                    if (tempradius < huntRadius) {
                        double targetAngle = Math.atan2(dy, dx);
                        turnToward(Updateboids.get(n), targetAngle, 0.4);   // stronger turn for separation
                    }
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

                    turnToward(Updateboids.get(n), desiredAngle, 0.25);


                }
            }
        }
    }


    //sepration
    public void Sepration(int n, Boid current, int i){

        if (i != n) {
            if (0 == boids.get(i).type) {
                int dx = current.x - boids.get(i).x;
                int dy = current.y - boids.get(i).y;
                double tempradius = Math.sqrt(dx * dx + dy * dy);
                if (tempradius < separationRadius && tempradius != 0) {
                    double targetAngle = Math.atan2(dy, dx);
                    turnToward(Updateboids.get(n), targetAngle, 0.4);   // stronger turn for separation
                }
            }
        }
    }

    // cohrention


    public void Cohrention(int n, Boid current, int i){


        double centerX = 0;
        double centerY = 0;
        int count = 0;

            if(i != n) {

                Boid other = boids.get(i);
                if (other.type == 0) {
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
    public void Alignment(int n, Boid current, int i){

        double sumX = 0;
        double sumY = 0;
        int count = 0;


            if(i != n) {

            Boid other = boids.get(i);
            if (other.type == 0){
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
