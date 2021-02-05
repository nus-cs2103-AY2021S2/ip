package Commands;

import Tasks.TaskList;
import Tasks.ToDo;

public class AddToDoCommand extends AddCommand {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasks) {
        ToDo toDo = new ToDo(this.description);
        this.executeAddTask(tasks, toDo);
    }
}
