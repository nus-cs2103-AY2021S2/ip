package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * class ToDoCommand
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent an executable command that corresponds to the user input "todo"
 */
public class ToDoCommand extends Command{
    private String toDoDescription;

    /**
     * Constructor: creates a new ToDoCommand
     * @param toDoDescription description of ToDo
     */
    public ToDoCommand(String toDoDescription) {
        this.toDoDescription = toDoDescription;
    }

    /**
     * execute: executes the command
     * @param tasks the list of tasks
     * @param ui
     * @param storage
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task toDo = new ToDo(toDoDescription);
            tasks.addTask(toDo);
            ui.showAddTask(toDo, tasks);
            storage.saveFile(tasks.getTaskList());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    /**
     * isExit: checks if Duke should terminate
     * @return false
     */
    public boolean isExit() {
        return false;
    }
}
