package duke;

import javafx.application.Application;

/**
 * A launcher class to launch the main class.
 */
public class Launcher {
    /**
     * Entry point of the application.
     *
     * @param args an optional user-specified filepath used to initialize the storage
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}