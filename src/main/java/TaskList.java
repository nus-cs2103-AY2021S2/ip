import java.util.ArrayList;

public class TaskList {
    //contains the task list
    //e.g. it has operations to add/delete tasks in the list

    private static ArrayList<Task> taskList;
    
    public TaskList() { 
        this.taskList = new ArrayList<Task>();
    }
    
    public TaskList(ArrayList<Task> taskList) { 
        this.taskList = taskList;
    }
    
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
    
}
