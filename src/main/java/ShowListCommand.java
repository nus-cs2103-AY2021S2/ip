package main.java;

import java.util.ArrayList;

public class ShowListCommand implements Command {

    public ShowListCommand(){
    }

    @Override
    public void execute(TaskList dukeTaskList, Ui dukeUi, Storage dukeStorage) {
        ArrayList<Task> currentTaskList = dukeTaskList.getCurrentTaskList();
        Ui.showReturnTaskList(currentTaskList);
    }
}
