package main.java.duke.command;

import main.java.duke.maincomponents.Storage;
import main.java.duke.maincomponents.TaskList;
import main.java.duke.maincomponents.Ui;

/**
 * Interface for all command, which have an execute method that performs an action
 */

public interface Command {
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage);
}
