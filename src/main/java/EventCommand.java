package main.java;

import java.util.ArrayList;

public class EventCommand implements Command {
    ArrayList<String> eventDescription;
    public EventCommand(ArrayList<String> a){
        eventDescription = a;
    }


    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        Task eventTask = dukeTaskList.addEventTask(eventDescription);
        dukeUi.showAddedTask(eventTask, dukeTaskList.getNumberOfTasks());
    }


}
