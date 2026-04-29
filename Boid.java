public class Boid {
    private BoidsLogic parent;
    public int x, y;
    public double angle;   // direction the triangle points
    public double speed;   // constant forward speed

    public Boid(BoidsLogic p) {
        parent = p;

        x = (int)(Math.random() * parent.parent.parent.Width);
        y = (int)(Math.random() * parent.parent.parent.Height);

        angle = Math.random() * Math.PI * 2;
        speed = 3.0;
    }

    public double speedX() {
        return Math.cos(angle) * speed;
    }

    public double speedY() {
        return Math.sin(angle) * speed;
    }
}
