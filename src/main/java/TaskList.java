import java.util.ArrayList;

public class TaskList {
    // tasks in schedule
    private ArrayList<Task> taskList;

    public TaskList(){
        this.taskList = new ArrayList<Task> ();
    }
    public TaskList(ArrayList<String> myTasks){
//        load existing tasks

    }

    public int getSize(){
        return this.taskList.size();
    }

    public Task getTask(int taskNumber){
        Task task = this.taskList.get(taskNumber);
        return task;
    }

    public void add(Task task){
        this.taskList.add(task);
    }

    public void removeTask(int taskNumber){
        this.taskList.get(taskNumber);
    }
}
