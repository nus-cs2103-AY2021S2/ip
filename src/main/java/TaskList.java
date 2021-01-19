import java.util.ArrayList;
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
        return "Gotcha. I've added the task: \n    " 
                + task 
                + "\nNow you have " + taskList.size() + " task(s) in your list";
    }

    public String markTaskDone(int position) {
        taskList.get(position).markDone();
        return "Nice, another job well done!\n" 
            + taskList.get(position).toString();
    }

    public String listTasks() {
        return "Here is your list of tasks: \n" + Formatter.formatList(taskList
                .stream()
                .map(t -> t.toString())
                .collect(Collectors.toList())
        );
    }
}
