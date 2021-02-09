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
        setIsExit(true);
    }

    @Override
    public String execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showExit();
        return "I hope I have been of assistance. Goodbye. C:";
    }
}
