package todobeast;

/**
 * Main client class for the application.
 */
public class ToDoBeast {

    Application app;

    public ToDoBeast() {
        app = new Application();
    }

    public void runApplication() {
        app.runApplication();
    }

    /**
     *Runs the main logic of the application.
     * @param args not used in this method
     */
    public static void main(String[] args) {
        ToDoBeast toDoBeast = new ToDoBeast();
        toDoBeast.runApplication();
    }



}
