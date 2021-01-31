package duke;

import duke.Task;
import java.util.ArrayList;

//store tasks
public class TaskList {
    public ArrayList<Task> list;


    public TaskList() {
        list = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public Task deleteTask(int num) {
        return list.remove(num-1);
    }

    public void checkAsDone(Task task) {
        task.done();
    }

}
