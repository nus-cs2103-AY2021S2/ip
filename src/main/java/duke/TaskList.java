package duke;

import java.util.ArrayList;

import duke.task.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public int getSize() {
        return taskList.size();
    }

    public Task getIndex(int i) {
        return taskList.get(i);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void removeIndex(int i) {
        this.taskList.remove(i);
    }
}
