import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int idx) {
        return tasks.remove(idx - 1); // TaskList is 1-indexed
    }

    public Task get(int idx) {
        return tasks.get(idx - 1); // TaskList is 1-indexed
    }

    public int size() {
        return tasks.size();
    }

    public void clear() {
        tasks.clear();
    }

    public String display() {
        if (tasks.size() > 0) {
            StringBuilder items = new StringBuilder();
            for (int i = 0; i < tasks.size(); i++) {
                items.append(String.valueOf(i + 1) + ". " + tasks.get(i).toString() + "\n");
            }
            return items.toString().trim();
        } else {
            return "Your to-do list is empty! Nice!";
        }
    }
}
