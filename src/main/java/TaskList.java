import java.util.ArrayList;
import java.util.List;


public class TaskList {
    private Storage storage;
    private List<Task> tasks;
    private int count;

    public TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<>();

    }


    public Task get(int index) {
        return this.tasks.get(index - 1);
    }

    public void remove(int index) {
        this.storage.remove(index);
        this.tasks.remove(index -1);
        this.count--;
    }

    public void set(int index, Task task) {
        this.storage.set(index, task);
        this.tasks.set(index -1, task );
    }

    public void add(Task task) {
        this.storage.add(task);
        this.tasks.add(task);
        this.count++;
    }

    public List<Task> find (String str){
        List<Task>results = new ArrayList<>();
        for (Task t : tasks){
            if(t.getName().contains(str)){
                results.add(t);
            }
        }
        return results;
    }

    public int getCount() {
        return this.count;
    }

}
