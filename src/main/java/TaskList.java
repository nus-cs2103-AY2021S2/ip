import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class TaskList {
    List<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> savedData) {
        tasks = savedData;
    }

    boolean hasTasks() {
        return tasks.size() > 0;
    }

    Task getTask(int idx) {
        return tasks.get(idx - 1);
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void markTaskAsDone(int idx) {
        tasks.get(idx - 1).markAsDone();
    }

    Task deleteTask(int idx) {
        return tasks.remove(idx - 1);
    }

    int getTotalTasksUndone() {
        return tasks.stream()
                .mapToInt(Task::isNotDone)
                .reduce(0, Integer::sum);
    }

    @Override
    public String toString() {
        if (this.hasTasks()) {
            // adapted from: https://stackoverflow.com/questions/49080255/get-index-while-iterating-list-with-stream
            String taskList =  IntStream.range(0, tasks.size())
                    .mapToObj(index -> String.format("  %s. %s\n",
                            index, tasks.get(index).toString()))
                    .reduce((a, b) -> a + b)
                    .get();
        }
        return "";
    }
}
