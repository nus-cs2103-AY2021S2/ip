package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.DukeResponses;

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
     * @param dukeResponses
     * @param storage
     * @return add todo success message, or
     *         an error message
     */
    public String execute(TaskList tasks, DukeResponses dukeResponses, Storage storage) {
        try {
            Task toDo = new ToDo(toDoDescription);
            tasks.addTask(toDo);
            storage.saveFile(tasks.getTaskList());
            return dukeResponses.showAddTask(toDo, tasks);
        } catch (DukeException e) {
            return dukeResponses.showError(e);
        }
    }
}
