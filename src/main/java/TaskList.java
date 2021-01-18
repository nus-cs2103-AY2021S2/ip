import java.util.ArrayList;

public class TaskList {
    private ArrayList<String> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<String> getTaskList() {
        return this.taskList;
    }

    public String addTask(String task) {
        taskList.add(task);
        return "added: " + task;
    }

    public String listTasks() {
        return Formatter.formatList(taskList);
    }
}
