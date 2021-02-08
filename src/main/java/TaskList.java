import java.util.ArrayList;
import java.time.LocalDate;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int taskNum) {
        return this.tasks.get(taskNum - 1);
    }

    public void done(int taskNum) {
        this.tasks.get(taskNum - 1).setCompletion(true);
    }

    public void delete(int taskNum) {
        this.tasks.remove(taskNum - 1);
    }

    public int size() {
        return this.tasks.size();
    }

    public TaskList getTasksOnDate(LocalDate date) {
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        for (int i = 1; i <= this.tasks.size(); i++) {
            Task currTask = this.tasks.get(i);
            if (date.equals(currTask.getDate())) {
                tasksOnDate.add(currTask);
            }
        }
        return new TaskList(tasksOnDate);
    }

    @Override
    public String toString() {
        StringBuilder taskListString = new StringBuilder();
        for (int i = 1; i <= this.tasks.size(); i++) {
            taskListString.append(i + "." + this.get(i).toString() + "\n");
        }
        return taskListString.toString();
    }

}
