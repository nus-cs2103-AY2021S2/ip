import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method of Launcher which launches the application in Main class
     * @param args array of Strings passed as parameters
     */
    public static void main(String[] args) {
        assert(args != null);
        Application.launch(Main.class, args);
    }
}
