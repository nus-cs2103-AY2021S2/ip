import java.util.ArrayList;

public class TaskList {

    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>(100);
    }

    public int addToDo(String description) {
        this.tasks.add(new ToDo((description)));
        return tasks.size() - 1;
    }

    public int addDeadline(String description, String by) {
        this.tasks.add(new Deadline(description, by));
        return tasks.size() - 1;
    }

    public int addEvent(String description, String at) {
        this.tasks.add(new Event(description, at));
        return tasks.size() - 1;
    }

    public int deleteTask(int task) {
        tasks.remove(task);
        return tasks.size() - 1;
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
