package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class ToDoCommand extends Command{
    private CommandWord commandWord = CommandWord.TODO;
    private String toDoDescription;

    public ToDoCommand(String todoDescription) {
        this.toDoDescription = todoDescription;
    }

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

    public boolean isExit() {
        return false;
    }
}
