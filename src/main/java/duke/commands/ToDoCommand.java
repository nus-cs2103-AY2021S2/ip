package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.utils.Storage;
import duke.utils.Ui;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";

    private String todo;

    public ToDoCommand(TaskList taskList, Ui ui, Storage storage, String todo) {
        super(taskList, ui, storage);
        this.todo = todo;
    }

    @Override
    public void execute() {
        ToDo t = new ToDo(this.todo);
        System.out.println("Got it. I've added this task:\n" + t);
        this.taskList.addTask(t);
    }
}
