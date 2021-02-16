package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    /** Main method which initializes and runs Duke.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
