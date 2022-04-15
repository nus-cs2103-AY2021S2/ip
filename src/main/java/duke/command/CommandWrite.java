package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;

/**
 * Wrapper Command that writes all tasks from taskList to file
 * after executing the given command.
 */
public class CommandWrite implements ICommand {
    private ICommand decoratedCommand;
    private Storage storage;
    private Ui ui;

    /**
     * Creates the command that will write to file when decoratedCommand
     * is executed.
     *
     * @param decoratedCommand Command to executed before writing to file.
     * @param ui Ui object responsible for displaying the output.
     * @param storage Object responsible for writing the contents in taskList to file.
     */
    public CommandWrite(Ui ui, Storage storage, ICommand decoratedCommand) {
        this.ui = ui;
        this.storage = storage;
        this.decoratedCommand = decoratedCommand;
    }

    /**
     * Carry out writing to file after executing the command given.
     *
     * @param parameters necessary input needed for decoratedCommand to execute.
     */
    public void execute(String parameters) {
        try {
            decoratedCommand.execute(parameters);
            storage.write();
        } catch (IOException e) {
            ui.handleError(e);
        }
    }
}
