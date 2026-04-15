public class Handler {
    private BoidsLogic boidslogic;
    private BoidsGUI boidsGUI;


    public Handler(){
        boidslogic = new BoidsLogic(this);
        boidsGUI = new BoidsGUI(this);

    }
}
