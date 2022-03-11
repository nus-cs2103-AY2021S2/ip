package todobeast.commands;

import todobeast.TaskList;
import todobeast.Ui;
import todobeast.exceptions.ToDoBeastException;

/**
 * Abstract class that defines an implementation of a Command object. This object will contain the necessary business
 * logic for executing specific commands for the application.
 */
public abstract class Command {

    /**
     * Determines whether to exit the application or not.
     */
    protected final boolean isExit;

    public Command() {
        isExit = false;
    }

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Method that contains the necessary logic required to execute specific commands. To be implemented by the child
     * classes. Takes in the TaskList and Ui object specific to the class in order to implement the logic required.
     * @param taskList the TaskList that belongs to this application
     * @param ui the Ui that belongs to this application
     * @throws ToDoBeastException if there is an error while processing the business logic
     */
    public abstract void execute(TaskList taskList, Ui ui) throws ToDoBeastException;

    public boolean isExit() {
        return isExit;
    }
}
