package duke.controller;

import duke.task.Task;

/**
 * A class to handle the printing of the current status
 * of the list of Tasks.
 */
public class Ui {
    /**
     * Prints the startup message.
     */
    public String initialize() {
        return "Hello! I'm Duke's friend, Ekud." +
                "\nDuke's dead, so I'm here to take his job." +
                "\nYou want to jot down some tasks?\n";
    }

    /**
     * Prints the current status of the list of Tasks.
     *
     * @param preMessage Response when user's requested action is successful.
     * @param t Task involved in the action.
     * @param size Number of Tasks in the list.
     */
    public String printOnListChange(String preMessage, Task t, int size) {
        return preMessage + "\n" + t + "\n" +
                "Now you have " + size + " tasks in the list.\n";
    }
}
