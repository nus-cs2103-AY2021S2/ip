package chadbot;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 *
 * @author  arsatis
 * @version 1.2
 * @since   2021-02-08
 */
public class Launcher {

    /**
     * Default constructor for the Launcher class.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }

}
