package lihua.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
//@@author Cheng20010201
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor modifications.
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
//@@author
