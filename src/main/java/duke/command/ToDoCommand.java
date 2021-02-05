package duke.command;

import duke.maincomponents.Storage;
import duke.maincomponents.TaskList;
import duke.maincomponents.Ui;
import duke.task.Task;

public class ToDoCommand implements Command {
    private final String toDoDescription;

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
