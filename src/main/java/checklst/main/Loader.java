package checklst.main;

import javafx.application.Application;

/**
 * A launcher class to workaround classpath issues.
 */
public class Loader {

    public static void main(String[] args) {
        Application.launch(Checklst.class, args);
    }

}
