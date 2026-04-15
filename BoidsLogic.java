public class BoidsLogic {
public Handler parent;
public Boid boid;

    public BoidsLogic(Handler p){
        parent = p;
        boid = new Boid(this);

        Sepration();
        Cohrention();
        Alignment();
    }
    
    public void Updatepos(int x, int y) {

        x=(int)(x+boid.speedX);
        y=(int)(y+boid.speedY);
        if(!(parent.boidsGUI == null)) {
            parent.boidsGUI.boidspanel.repaint();
        }

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // bevar interrupt-status
        }
        Updatepos(x,y);
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
