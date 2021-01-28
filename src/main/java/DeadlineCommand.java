package main.java;

import java.util.ArrayList;

public class DeadlineCommand implements Command {
    ArrayList<String> eventDescription;
    public DeadlineCommand(ArrayList<String> a){
        eventDescription = a;
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        try {
            Task deadlineTask = dukeTaskList.addDeadlineTask(eventDescription);
            dukeUi.showAddedTask(deadlineTask, dukeTaskList.getNumberOfTasks());
        } catch (DukeException e) {
            dukeUi.showErrorMsg(e.getMessage());
        }
    }
}
