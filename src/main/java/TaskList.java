import java.util.ArrayList;
import java.util.List;

public class TaskList {

    protected List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public int getTaskCount() {
        return taskList.size();
    }

    public Task getTask(int i) {
        return this.taskList.get(i-1);
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    public void addTask(Task task) {
        this.taskList.add(task);
    }

    public void deleteTask(int taskIndex) {
        this.taskList.remove(taskIndex-1);
    }

    public boolean checkTaskExist(int index) {
        if (index < 1 || index >= this.taskList.size()) {
            return false;
        } else {
            return true;
        }
    }

    public void printTask() {
        int i = 1;
        for (Task t : this.taskList) {
            System.out.println(i + ". " + t.toString());
            i++;
        }
    }

}
