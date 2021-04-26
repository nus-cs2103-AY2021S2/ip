package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilException;
import vergil.types.exceptions.VergilFormatException;

/**
 * Represents a 'delete' command.
 */
public class DeleteCommand extends Command {
    private int taskIndex;
    private String taskAsString;

    /**
     * Constructs a new 'delete' command.
     *
     * @param   args    the 'delete' command arguments
     *                  (i.e., the list number of the task to delete).
     */
    public DeleteCommand(String... args) {
        super(CommandType.DELETE, args);
    }

    /**
     * Executes the 'delete' command and saves the list.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to delete from.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful deletion of the required task.
     * @throws VergilException  if the given argument is not a number, or if there are no tasks
     *                          in the list with the given list number.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        try {
            taskIndex = Integer.parseInt(getArgument(0));
            taskAsString = taskList.getAsString(taskIndex);

            taskList.delete(taskIndex);
            storage.save(taskList);

            return "Acknowledged. The following task has been deleted:\n"
                    + taskAsString;
        } catch (NumberFormatException e) {
            throw new VergilFormatException("You must enter a number.");
        }
    }
}
