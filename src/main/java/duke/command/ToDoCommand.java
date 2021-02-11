package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;
import duke.tasks.ToDo;

/**
 * Command to create a ToDo task.
 */
public class ToDoCommand extends Command {
    public static final boolean IS_EXIT = false;
    protected String input;

    /**
     * Constructor method
     * @param input The input command from the user.
     */
    public ToDoCommand(String input) {
        super(IS_EXIT);
        this.input = input;
    }

    /**
     * Executes method for ToDo command.
     * @param tasks The tasks in the TaskList.
     * @param ui Standard UI object
     * @param storage Standard storage object
     * @throws DukeException if the description of command is missing or if the user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty");
        }
        String s = input.substring(5);
        ToDo td = new ToDo(s);
        tasks.addTask(td);
        StringBuilder sb = new StringBuilder(ui.showTaskAdded(td) + "\n");
        storage.addNewDataToFile("T", "0", td.getDescription(), "");
        sb.append(ui.showNoOfItems(tasks));
        return sb.toString();
    }
}
