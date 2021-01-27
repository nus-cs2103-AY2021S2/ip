import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public ArrayList<Task> getList(){
        return this.tasks;
    }
    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }
    public void set(int i, Task task){
        this.tasks.set(i,task);
    }
    public Task get(int i){
        return tasks.get(i);
    }
    public void add(Task task){
        tasks.add(task);
    }
    public void remove(int i){
        tasks.remove(i);
    }
    public int size(){
        return tasks.size();
    }
}
