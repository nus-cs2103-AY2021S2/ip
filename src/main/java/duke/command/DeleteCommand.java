package main.java.duke.command;

import main.java.duke.exceptions.DukeException;
import main.java.duke.maincomponents.Storage;
import main.java.duke.maincomponents.TaskList;
import main.java.duke.maincomponents.Ui;
import main.java.duke.task.Task;

public class DeleteCommand implements Command {
    private int taskDeleteInt;
    public DeleteCommand(int i) {
        taskDeleteInt = i;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        try {
            Task deletedTask = dukeTaskList.deleteTask(taskDeleteInt);
            dukeUi.showTaskDeleted(deletedTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}

