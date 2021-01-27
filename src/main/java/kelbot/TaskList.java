package kelbot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TaskList implements Serializable {
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
        this.taskList.add(task);
    }
    
    public Task done(int taskNumber) {
        Task taskToComplete = taskList.get(taskNumber - 1);
        taskToComplete.complete();
        return taskToComplete;
    }
    
    public Task delete(int taskNumber) {
        Task taskToDelete = taskList.remove(taskNumber - 1);
        return taskToDelete;
    }
    
    /**
     * Searches through all the tasks to see which ones contains the given keyword
     *
     * @param keyword The keyword to be searched
     * @return The task list that contains only the relevant tasks
     */
    
    public ArrayList<Task> search(String keyword) {
        ArrayList<Task>taskListToPrint = new ArrayList<>();
        for (Task task: taskList) {
            if (task.hasKeyword(keyword)) {
                taskListToPrint.add(task);
            }
        }
        return taskListToPrint;
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
