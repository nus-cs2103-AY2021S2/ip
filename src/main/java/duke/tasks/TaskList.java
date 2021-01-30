package duke.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;


    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int taskIndex) {
        tasks.remove(taskIndex - 1);
    }

    public void updateTask(int taskIndex, Task task) {
        tasks.set(taskIndex - 1, task);
    }

    public int getNumOfTasks() {
        return tasks.size();
    }

    public Task getTask(int taskIndex) {
        return tasks.get(taskIndex - 1);
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            s += Integer.toString(i + 1) + "."
                    + t + "\n";
        }
        return s;
    }


}
