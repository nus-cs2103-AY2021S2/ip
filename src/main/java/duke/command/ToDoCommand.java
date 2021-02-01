package main.java.duke.command;

import main.java.duke.maincomponents.Storage;
import main.java.duke.maincomponents.TaskList;
import main.java.duke.maincomponents.Ui;
import main.java.duke.task.Task;

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
