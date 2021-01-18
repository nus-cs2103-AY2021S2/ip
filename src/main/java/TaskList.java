import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public String addTask(Task task) {
        taskList.add(task);
        return "added: " + task;
    }

    public String addTask(String taskDesc) {
        taskList.add(new Task(taskDesc));
        return "added: " + taskDesc;
    }

    public String listTasks() {
        return Formatter.formatList(taskList
                .stream()
                .map(t -> t.toString())
                .collect(Collectors.toList())
        );
    }
}
