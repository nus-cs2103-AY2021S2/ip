package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;

/**
 * Represents a 'bye' (i.e., exit) command.
 */
public class ByeCommand extends Command {
    /**
     * Constructs a new 'bye' command.
     */
    public ByeCommand() {
        super(CommandType.BYE);
    }

    /**
     * Executes the 'bye' command.
     *
     * @param   ui          the Ui object containing Vergil's farewell message.
     * @param   taskList    the task list which the command would operate on.
     * @param   storage     the Storage object the command is going to use.
     * @return              Vergil's farewell message.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.getFarewellMessage();
    }
}
