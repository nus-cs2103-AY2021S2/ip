import java.util.ArrayList;

public class TaskList {
    
    private final ArrayList<Task> taskList = new ArrayList<>();

    public void add(Task task) {
        this.taskList.add(task);
    }

    public void replace(int index, Task newTask) {
        this.taskList.set(index, newTask);
    }
 
    public Task get(int index) {
        return this.taskList.get(index);
    }

    @Override
    public String toString() {
        if (taskList.size() == 0) {
            return "Task list is currently empty!";
        }

        String taskListOutput = "1. " + taskList.get(0);
        for (int i = 1; i < taskList.size(); i++) {
            taskListOutput += "\n\t" + String.valueOf(i + 1) + ". " + taskList.get(i);
        }
        return taskListOutput;
    }

}
