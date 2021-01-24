import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
    
    public int size() {
        return taskList.size();
    }

    public void addTask(Task task) {
        taskList.add(task);
//        printAddedMessage(task);
    }
    
    public void deleteTask(int index) {
        taskList.remove(index);
    }
    
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
