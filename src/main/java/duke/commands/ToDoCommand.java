package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.utils.Storage;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String todo;

    /**
     * Creates a ToDoCommand object to store the todo command input from the user.
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param todo the task input by the user.
     */
    public ToDoCommand(TaskList taskList, Storage storage, String todo) {
        super(taskList, storage);
        this.todo = todo;
    }

    /**
     * Adds a ToDo task with previously specified description to taskList.
     * @return message confirming that indicated ToDo task is added.
     */
    @Override
    public String execute() {
        ToDo t = new ToDo(this.todo);
        this.taskList.addTask(t);
        return "Got it. I've added this task:\n" + t;
    }
}
