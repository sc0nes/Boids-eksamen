public class BoidsLogic {
public BoidsPanel parent;
public Boid boid;
boolean running;

    public BoidsLogic(BoidsPanel p){
        parent = p;
        boid = new Boid(this);
        running = true;

        Sepration();
        Cohrention();
        Alignment();
        Updatepos(boid.x, boid.y);
    }
    
    public void Updatepos(int x, int y) {

        x=(int)(x+boid.speedX);
        y=(int)(y+boid.speedY);
        parent.repaint();

        try {
            Thread.sleep(10);
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }


        while(running) {
            Updatepos(x, y);
        }
    }

    // sepration
    public void Sepration(){
        System.out.println("hello");
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
