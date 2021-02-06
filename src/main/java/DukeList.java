package duke;

import java.util.ArrayList;
import java.util.List;

public class DukeList {
    private List<Task> taskList;

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
