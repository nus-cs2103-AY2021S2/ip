package dbot;

import dbot.gui.Gui;
import javafx.application.Application;


/**
 * A launcher class to start the DBot GUI.
 */
public class Launcher {
    public static void main(String[] args) {
        Application.launch(Gui.class, args);
    }
}