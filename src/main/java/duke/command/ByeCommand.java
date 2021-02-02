package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a 'bye' command.
 * Exits Duke.
 */
public class ByeCommand extends Command {

    public ByeCommand(String arguments) {
        super(arguments);
        setExit(true);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.print("I hope I have been of assistance. Goodbye. C:");
    }
}
