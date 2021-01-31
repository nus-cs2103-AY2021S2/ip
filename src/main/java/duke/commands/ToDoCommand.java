package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.utils.Storage;
import duke.utils.Ui;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String todo;

    /**
     * Creates a ToDoCommand object to store the todo command input from the user.
     * @param taskList the current list of Tasks.
     * @param ui the object in charge of printing user-friendly outputs.
     * @param storage the object in charge of writing to the local storage file.
     * @param todo the task input by the user.
     */
    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String todo) {
        super(taskList, ui, storage);
        this.todo = todo;
    }

    /**
     * Adds a ToDo task with previously specified description to taskList.
     */
    @Override
    public void execute() {
        ToDo t = new ToDo(this.todo);
        System.out.println("Got it. I've added this task:\n" + t);
        this.taskList.addTask(t);
    }
}
