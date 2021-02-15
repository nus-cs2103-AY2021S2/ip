package duke;

import javafx.application.Application;

/**
 * class Launcher
 * Description: A launcher class to workaround classpath issues.
 */
public class Launcher {
    /**
     * main procedure
     * @param args
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}