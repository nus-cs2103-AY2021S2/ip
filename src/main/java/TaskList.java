import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> listOfTasks;

    TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public void add(Task task) {
        this.listOfTasks.add(task);
    }

    public void delete(int taskIndex) {
        this.listOfTasks.remove(taskIndex - 1);
    }

    public void print() {
        for (int i = 0; i < listOfTasks.size(); i++)
            System.out.println("    " + this.listOfTasks.get(i).getStatus());
    }

    public int numberOfTasks() {
        return this.listOfTasks.size();
    }

    public Task get(int taskIndex) {
        return this.listOfTasks.get(taskIndex);
    }
}
