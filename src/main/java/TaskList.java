import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    void deleteTask(int taskIndex) throws InvalidDescriptionException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.remove(taskIndex);
    }

    void markTask(int taskIndex) throws DukeException {
        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.get(taskIndex).markAsDone();
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    void addTask(Task task) {
        tasks.add(task);
    }
}
