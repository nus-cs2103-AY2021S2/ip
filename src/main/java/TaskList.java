import java.util.*;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task getTaskByIndex(int index) {
        return this.tasks.get(index - 1);
    }

    public void printTasks() {
        for (int index = 0; index < this.tasks.size(); index++) {
            int taskNumber = index + 1;
            Task task = this.getTaskByIndex(taskNumber);
            System.out.print(taskNumber + "." + task.getStatusString() + "\n");
        }
    }

    public int getSize() {
        return this.tasks.size();
    }
}
