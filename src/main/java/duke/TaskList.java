package duke;

import duke.task.Task;

import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public Task get(int index) {
        return taskList.get(index);
    }

    public boolean add(Task task) {
        return taskList.add(task);
    }

    public Task remove(int index) {
        return taskList.remove(index);
    }

    public int size() {
        return taskList.size();
    }
}
