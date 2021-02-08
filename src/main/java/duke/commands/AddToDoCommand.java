package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Handles the logic of adding a to-do task to the to-do list.
 */
public class AddToDoCommand extends Command {
    private final String description;

    /**
     * Initializes a command to add a to-do task with a description and a datetime.
     *
     * @param description Description of the to-do task.
     */
    public AddToDoCommand(String description) {
        this.description = description;
    }

    public boolean isExit() {
        return false;
    }

    /**
     * Creates a <code>ToDo</code> object and adds it to the input <code>TaskList</code>.
     * Then, prints responses to the notify the users.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui A handler to manage the application's user-interface layer.
     */
    public void execute(TaskList tasks, Ui ui) {
        ToDo toDo = new ToDo(this.description);
        tasks.addTask(toDo);
        ui.handleAddTask(tasks, toDo);
    }
}
