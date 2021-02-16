package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

/**
 * ToDo Command, which adds a todo task to Duke's TaskList when executed
 */

public class ToDoCommand implements Command {
    private final String toDoDescription;

    /**
     * Default constructor for the Todo class
     *
     * @param s description of the todo task
     */
    public ToDoCommand(String s) {
        toDoDescription = s;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task toDoTask = dukeTaskList.addToDoTask(toDoDescription);
        dukeUi.showAddedTask(toDoTask, dukeTaskList.getNumberOfTasks());
    }

    @Override
    public String executeGui(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task toDoTask = dukeTaskList.addToDoTask(toDoDescription);
        return dukeUi.returnAddedTask(toDoTask, dukeTaskList.getNumberOfTasks());
    }
}
