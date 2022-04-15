package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.utils.Storage;

/**
 * Represents a todo command which adds a todo task to the task list.
 */
public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private final String todo;

    /**
     * Creates a ToDoCommand object to store the todo command input from the user.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param todo the String task input by the user.
     */
    public ToDoCommand(TaskList taskList, Storage storage, String todo) {
        super(taskList, storage);
        this.todo = todo;
    }

    /**
     * Adds a ToDo task with previously specified description to taskList.
     *
     * @return message confirming that indicated ToDo task is added.
     */
    @Override
    public String execute() {
        ToDo todo = new ToDo(this.todo);
        this.taskList.addTask(todo);
        String successAddMsg = "Got it. I've added this task:\n" + todo;
        return successAddMsg;
    }
}
