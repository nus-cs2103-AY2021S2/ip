package duke;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> list;

    public TaskList(){
        this.list = new ArrayList<>();
    }

    public void addTask(Task t){
        list.add(t);
    }
    public void deleteTask(int index){
        list.remove(index);
    }

}
