package duke;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Entry point to the Duke chat bot.
     *
     * @param args Arguments supplied to the application.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
