package duke.commands;

import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui) {
        ToDo toDo = new ToDo(this.description);
        tasks.addTask(toDo);
        ui.handleAddTask(tasks, toDo);
    }
}
