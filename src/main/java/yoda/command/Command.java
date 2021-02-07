package yoda.command;

import yoda.storage.Storage;
import yoda.task.TaskList;
import yoda.ui.Ui;

/**
 * Command class to handle user inputs.
 */
public abstract class Command {
    /** Details of user input */
    protected String[] details;
    /** Type of command referred to by user input */
    protected CommandType commandType;

    /**
     * Creates a command object.
     */
    public Command() {}

    /**
     * Creates a command object.
     * @param details Details required to handle user input.
     */
    public Command(String[] details) {
        this.details = details;
    }

    /**
     * Executes the command.
     * @param taskList TaskList associated with the command being executed.
     * @param ui Ui associated with the command being executed.
     * @param storage Storage associated with the command being executed.
     */
    public abstract String execute (TaskList taskList, Ui ui, Storage storage);
}
