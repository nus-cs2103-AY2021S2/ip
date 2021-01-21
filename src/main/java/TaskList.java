import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public Task getTask(int id) {
        int index = id - 1;
        return tasks.get(index);
    }

    public int getSize() {
        return tasks.size();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void removeTask(int id) {
        tasks.remove(id - 1);
    }

    public void markDone(int id) {
        int index = id - 1;
        Task task = tasks.remove(index);
        task.markDone();
        tasks.add(index, task);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1) + ". ");
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
