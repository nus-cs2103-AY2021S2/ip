package vergil.types.commands;

import vergil.components.Storage;
import vergil.components.TaskList;
import vergil.components.Ui;
import vergil.types.Todo;
import vergil.types.exceptions.VergilException;

/**
 * Represents a 'todo' command.
 */
public class TodoCommand extends Command {
    private String desc;

    /**
     * Creates a new 'todo' command.
     *
     * @param   args    the 'todo' command arguments
     *                  (i.e., the task's description).
     */
    public TodoCommand(String... args) {
        super(CommandType.TODO, args);
    }

    /**
     * Executes the 'todo' command and saves the list.
     *
     * @param   ui              the Ui object containing Vergil's responses.
     * @param   taskList        the list of tasks to add the to-do task to.
     * @param   storage         the Storage object the command requires to perform saving operations.
     * @return                  Vergil's response to the successful execution of the 'todo' command.
     * @throws VergilException  if there's another task clashing with the task the command is trying
     *                          to add (not used for this type of tasks).
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage) throws VergilException {
        desc = getArgument(0);

        taskList.add(new Todo(desc));
        storage.save(taskList);

        return "Success! '" + desc + "' has been added as a todo task.";
    }
}
