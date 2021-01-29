package com.tjtanjin.steve;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {

    /**
     * Launches the Steve class.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        Application.launch(Steve.class, args);
    }
}
