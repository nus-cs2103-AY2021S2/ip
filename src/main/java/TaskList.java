import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public void addTask(String description) {
        this.tasks.add(new Task((description)));
    }

    public void completeTask(int i) {
        tasks.get(i).markAsDone();
    }

    public Task getTask(int i) {
        return this.tasks.get(i);
    }

    public int getTotalTasks() {
        return tasks.size();
    }

    @Override
    public String toString(){
        return Templates.taskListMsg(this.tasks);
    }



}
