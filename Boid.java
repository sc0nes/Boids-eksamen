import java.util.Vector;

public class Boid {
    private BoidsLogic parent;
    int x;
    int y;
    Vector<Double> pos = new Vector<Double>();
    int[] posi = new int[3];
    public Boid(BoidsLogic p){
        parent = p;
        pos.set(0, 1d);
        pos.set(1, 2d);
        pos.add(2d);

    }
}
