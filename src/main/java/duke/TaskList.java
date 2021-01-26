package duke;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> list = new ArrayList<>();
    protected TaskList(){
    }

    protected Task addTask(Task task) {
        list.add(task);
        return task;
    }

    protected Task doTask(int taskNum) {
        Task curr = list.get(taskNum - 1);
        curr.isDone = true;
        return curr;
    }

    protected Task delete(int num) throws ArrayIndexOutOfBoundsException {
        Task curr = list.get(num - 1);
        list.remove(num - 1);
        return curr;
    }
    // I'll need to change this later
    protected void bye(Duke duke) {
        duke.isOn = false;
    }
}
