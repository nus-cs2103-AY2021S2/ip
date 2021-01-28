package task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> savedData) {
        tasks = savedData;
    }

    public boolean hasTasks() {
        return tasks.size() > 0;
    }

    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTaskAsDone(int idx) {
        tasks.get(idx - 1).markAsDone();
    }

    public Task deleteTask(int idx) {
        return tasks.remove(idx - 1);
    }

    public int getTotalNumberOfTasks() {
        return tasks.size();
    }

    public int getTotalNumberOfTasksUndone() {
        return tasks.stream()
                .mapToInt(Task::isNotDone)
                .reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        if (this.hasTasks()) {
            // adapted from: https://stackoverflow.com/questions/49080255/get-index-while-iterating-list-with-stream
            return IntStream.range(0, tasks.size())
                    .mapToObj(index -> String.format("  %s. %s\n",
                            index+1, tasks.get(index).toString()))
                    .reduce((a, b) -> a + b)
                    .get();
        }
        return "";
    }
}
