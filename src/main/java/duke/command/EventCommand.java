package main.java.duke.command;

import main.java.duke.maincomponents.Storage;
import main.java.duke.task.Task;
import main.java.duke.maincomponents.TaskList;
import main.java.duke.maincomponents.Ui;

import java.util.ArrayList;

public class EventCommand implements Command {
    private ArrayList<String> eventDescription;
    public EventCommand(ArrayList<String> a) {
        eventDescription = a;
    }


    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task eventTask = dukeTaskList.addEventTask(eventDescription);
        dukeUi.showAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
    }


}
