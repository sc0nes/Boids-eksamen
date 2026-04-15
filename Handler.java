public class Handler {
    public BoidsLogic boidslogic;
    public BoidsGUI boidsGUI;


    public Handler(){
        boidslogic = new BoidsLogic(this);
        boidsGUI = new BoidsGUI(this);

    }
}
