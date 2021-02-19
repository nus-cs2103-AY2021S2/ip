package vergil.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Main method to run the Vergil application through this launcher.
     *
     * @param args arguments given to Vergil (not used).
     */
    public static void main(String[] args) {
        Application.launch(Vergil.class, args);
    }
}