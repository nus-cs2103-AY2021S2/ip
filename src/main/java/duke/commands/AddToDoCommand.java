package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * Handles the logic of adding a <code>ToDo</code> to the to-do list.
 */
public class AddToDoCommand extends Command {
    private final ToDo toDo;

    /**
     * Initializes a command to add a <code>ToDo</code> with a description.
     *
     * @param description Description of the to-do task.
     */
    public AddToDoCommand(String description) {
        this.toDo = new ToDo(description);
    }

    /**
     * Returns false as adding <code>ToDo</code> tasks should not terminate the application.
     *
     * @return false
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the created <code>ToDo</code> to the input <code>TaskList</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     */
    public void execute(TaskList tasks) {
        tasks.addTask(this.toDo);
    }

    /**
     * Computes a response to notify the users the adding of the <code>ToDo</code>.
     *
     * @param tasks A collection of <code>Task</code> objects representing the application's state.
     * @param ui    A handler to manage the application's user-interface layer.
     * @return A <code>String</code> to respond to the adding of the <code>ToDo</code>.
     */
    public String getResponse(TaskList tasks, Ui ui) {
        return ui.handleAddTask(tasks, this.toDo);
    }
}
