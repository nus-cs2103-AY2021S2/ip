package kelbot;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    public int getSize() {
        if (taskList.isEmpty()) {
            return 0;
        } else {
            return taskList.size();
        }
    }
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    public void add(Task task) {
        taskList.add(task);
    }
    
    public Task complete(int taskNumber) {
        Task taskToComplete = taskList.get(taskNumber - 1);
        taskToComplete.complete();
        return taskToComplete;
    }
    
    public Task delete(int taskNumber) {
        Task taskToDelete = taskList.remove(taskNumber - 1);
        return taskToDelete;
    }
    
    @Override
    public String toString() {
        String result = "";
        for (int i = 1; i <= taskList.size(); i++) {
            if (i == taskList.size()) {
                result += i + "." + taskList.get(i - 1);
            } else {
                result += i + "." + taskList.get(i - 1) + "\n";
            }
        }
        return result;
    }
}
