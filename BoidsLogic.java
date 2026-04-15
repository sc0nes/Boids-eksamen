public class BoidsLogic {
private Handler parent;
private Boid boid;

    public BoidsLogic(Handler p){
        parent = p;
        boid = new Boid(this);

        Sepration();
        Cohrention();
        Alignment();
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
