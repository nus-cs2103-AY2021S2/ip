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

    public void addTask(String description) {
        int taskID = tasks.size() + 1;
        Task newTask = new Task(description, taskID);
        tasks.add(newTask);
    }

    public void markDone(int id) {
        int index = id - 1;
        Task task = tasks.remove(index);
        tasks.add(index, task.markDone());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n");
        for(int i = 0; i < tasks.size(); i++) {
            sb.append(tasks.get(i).toString());
            sb.append("\n");
        }
        return sb.toString();
    }

}
