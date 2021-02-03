package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class ToDoCommand implements Command {
    private String toDoDescription;

    public ToDoCommand(String s) {
        toDoDescription = s;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task toDoTask = dukeTaskList.addToDoTask(toDoDescription);
        dukeUi.showAddedTask(toDoTask, dukeTaskList.getNumberOfTasks());
    }
}
