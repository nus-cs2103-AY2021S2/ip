import java.util.List;
import java.util.ArrayList;

public class TaskManager {
    public List<Task> taskList;

    public TaskManager() {
        taskList = new ArrayList<>();
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public Task getTask(int taskNumber) {
        return taskList.get(taskNumber - 1);
    }

    public int getNumOfTasks() {
        return taskList.size();
    }

    public void deleteTask(int taskNumber) {
        taskList.remove(taskNumber - 1);
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public String returnTaskListAsString() {
        StringBuilder outputString = new StringBuilder();
        for (Task task : taskList) {
            outputString.append(task.toString());
            outputString.append("\n");
        }
        return outputString.toString();
    }

}
