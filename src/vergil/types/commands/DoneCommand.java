package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.exceptions.VergilFormatException;
import vergil.types.exceptions.VergilException;

/**
 * Represents a 'done' command.
 */
public class DoneCommand extends Command {
    private int taskIndex;
    private String taskAsString;

    /**
     * Constructs a new 'done' command.
     *
     * @param   args    the 'done' command arguments
     *                  (i.e., the list number of the task to be marked as done).
     */
    public DoneCommand(String... args) {
        super(CommandType.DONE, args);
    }

    /**
     * Executes the 'done' command and saves the list.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks containing the task to be marked as done.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful marking of the specified task.
     * @throws VergilException  if the given argument is not a number, or if there are no tasks
     *                          in the list with the given list number.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        try {
            taskIndex = Integer.parseInt(getArgument(0));

            taskList.complete(taskIndex);
            storage.save(taskList);

            taskAsString = taskList.getAsString(taskIndex);

            return "Success! The following task has been completed:\n"
                    + taskAsString;
        } catch (NumberFormatException e) {
            throw new VergilFormatException("You must enter a number.");
        }
    }
}
