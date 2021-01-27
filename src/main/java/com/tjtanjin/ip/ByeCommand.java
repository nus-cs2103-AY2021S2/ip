package com.tjtanjin.ip;

/**
 * Entry point for handling logic and execution of bye command.
 */
public class ByeCommand {

    /**
     * Exits the program.
     */
    public void execute() {
        Ui.showInfo("Bye! See you later :D");
        System.exit(0);
    }
}
