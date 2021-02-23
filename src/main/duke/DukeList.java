package main.duke;

import java.util.ArrayList;
import java.util.List;
import main.duke.tasktype.Task;

public class DukeList {
    private List<Task> taskList;

    /**
     * Constructor for Duke list
     */
    public DukeList(){
        taskList = new ArrayList<>();
    }

    public DukeList(List<Task> taskList){
        this.taskList = taskList;
    }

    public List<Task> getTaskList(){
        return taskList;
    }

}
