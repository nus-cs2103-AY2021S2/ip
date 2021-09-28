package duke.gui;

import duke.Main;
import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * Launches the application.
     *
     * @param args command line argument
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
