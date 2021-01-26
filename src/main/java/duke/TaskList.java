import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(List<Task> taskList) {
        this.tasks = taskList;
    }

    public String addTask(Task task) {
        this.tasks.add(task);
        return "Got it. I've added this task:\n    "
                + task.toString()
                + this.sizeToString();
    }

    public String deleteTask(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > this.tasks.size()) {
            throw new DukeException("A task with this number does not exist.");
        }
        Task deletedTask = tasks.remove(taskIndex - 1);
        return "Noted. I have removed this task:\n    "
                + deletedTask
                + this.sizeToString();
    }
    
    public String doTask(int taskIndex) throws DukeException {
        if (taskIndex <= 0 || taskIndex > this.tasks.size()) {
            throw new DukeException("A task with this number does not exist.");
        }
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return "Nice! I've marked this task as done:\n      "
                + task.toString();
    }

    public String listTasks() {
        return this.toString();
    }

    public String saveTaskListString() {
        String str = "";
        for (Task t: this.tasks) {
            str += t.saveTaskString();
            str += "\n";
        }
        return str;
    }

    private String sizeToString() {
        return "\n    Now you have " + this.tasks.size() + " tasks in the list.";
    }

    @Override
    public String toString() {
        String str = "";
        if (this.tasks.size() == 0) {
            return str;
        }
        for (int i = 0; i < this.tasks.size(); i++) {
            str += String.valueOf(i + 1) + ": " + this.tasks.get(i) + "\n    ";
        }

        return str.substring(0, str.length() - 5);
    }
}