package fakebot;

import javafx.application.Application;


/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(FakeBot.class, args);
    }
}
