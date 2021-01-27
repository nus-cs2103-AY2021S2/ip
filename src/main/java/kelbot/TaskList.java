package kelbot;

import java.io.Serializable;
import java.util.ArrayList;

public class TaskList implements Serializable {
    private ArrayList<Task> taskList;
    
    /*
     * Initializes Task List with new taskList
     */
    
    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    
    /*
     * Initializes Task List with existing taskList
     */
    
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    
    /*
     * Returns Size of taskList
     */
    
    public int getSize() {
        if (taskList.isEmpty()) {
            return 0;
        } else {
            return taskList.size();
        }
    }
    
    /*
     * Returns Task List
     */
    
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    /*
     * Add Task to Task List
     */
    
    public void add(Task task) {
        this.taskList.add(task);
    }
    
    /*
     * Mark a Task in Task List as Done by Task Number
     */
    
    public Task done(int taskNumber) {
        Task taskToComplete = taskList.get(taskNumber - 1);
        taskToComplete.complete();
        return taskToComplete;
    }
    
    /*
     * Delete a Task in Task List by Task Number
     */
    
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
