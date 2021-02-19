package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;

/**
 * Represents a 'list' command.
 */
public class ListCommand extends Command {
    /**
     * Constructs a new 'list' command.
     */
    public ListCommand() {
        super(CommandType.LIST);
    }

    /**
     * Executes the 'list' command.
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to be displayed.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the execution of the 'list' command,
     *                          including the contents of the list.
     */
    public String execute(Ui ui, TaskList taskList, Storage storage) {
        return ui.getListResponse(taskList);
    }
}
