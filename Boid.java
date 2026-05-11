public class Boid {
    private BoidsLogic parent;
    public int x, y;
    public double angle;   // direction the triangle points
    public double speed;   // constant forward speed
    public double preyBonus; //exstra speed prey gets
    public int type;

    public Boid(BoidsLogic p) {
        parent = p;

        if (Math.random() < 0.1){
            type = 1;  // predetors
        } else type = 0; // preys


        x = (int)(Math.random() * parent.parent.parent.Width);
        y = (int)(Math.random() * parent.parent.parent.Height);

        angle = Math.random() * Math.PI * 2;
        speed = 2.5; // const speed no matter the angle
        preyBonus = 1.0;
    }

    public double speedXPrey() {
        return Math.cos(angle) * (speed+preyBonus);
    }

    public double speedYPrey() {
        return Math.sin(angle) * (speed+preyBonus);
    }
    public double speedXPredetors() {
        return Math.cos(angle) * speed;
    }

    public double speedYPredetors() {
        return Math.sin(angle) * speed;
    }
}
