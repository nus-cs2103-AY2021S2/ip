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

    public static void main(String[] args) {
        ToDoBeast toDoBeast = new ToDoBeast();
        toDoBeast.runApplication();
    }



}
