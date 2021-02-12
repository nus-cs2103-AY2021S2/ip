// Launcher implementation adapted from https://se-education.org/guides/tutorials/javaFxPart4.html

package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
