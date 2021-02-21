package yoda;

import javafx.application.Application;
import yoda.ui.Gui;

/**
 * YodaLauncher class to launch the Yoda chatbot.
 */
public class YodaLauncher {
    /**
     * Launches the application.
     * @param args Arguments required for application launch.
     */
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}
