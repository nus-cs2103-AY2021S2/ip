package duke;

import javafx.application.Application;

/**
 * <code>Launcher</code> class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(duke.Main.class, args);
    }
}
