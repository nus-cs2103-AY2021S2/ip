package duke;

import duke.task.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>();
        this.tasks.addAll(tasks);
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task delete(int index) {
        return tasks.remove(index - 1);
    }

    public Task mark(int index) {
        Task taskToBeMarked = tasks.get(index - 1);
        taskToBeMarked.markedAsDone();
        return taskToBeMarked;
    }
}
