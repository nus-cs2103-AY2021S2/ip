import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    TaskList() {
        tasks = new ArrayList<>();
    }

    TaskList(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    int getNumTasks() {
        return tasks.size();
    }

    String queryNumTasks() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    Task getTask(int i){
        return tasks.get(i);
    }

    void addTask(Task task) {
        tasks.add(task);
    }

    void deleteTask(int i) {
        tasks.remove(i);
    }
}
