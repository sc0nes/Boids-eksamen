public class Handler {
    public BoidsLogic boidslogic;
    public BoidsGUI boidsGUI;


    public Handler(){

        boidsGUI = new BoidsGUI(this);
        boidslogic = new BoidsLogic(this);
    }
}
