package yoda.command;

import yoda.task.TaskList;
import yoda.ui.Ui;
import yoda.storage.Storage;

/**
 * Command class to handle user inputs.
 */
public abstract class Command {
    /** Details of user input */
    protected String[] details;
    /** Type of command referred to by user input */
    protected CommandType taskType;
    /** Shows if the user is done using the Yoda chatbot */
    protected boolean isExit;

    /**
     * Creates a command object.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Creates a command object.
     * @param details Details required to handle user input.
     */
    public Command(String[] details) {
        this.details = details;
        this.isExit = false;
    }

    /**
     * Checks if the user is done using the Yoda chatbot.
     * @return Whether the user is done using the Yoda chatbot.
     */
    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     */
    public abstract void execute (TaskList taskList, Ui ui, Storage storage);
}
