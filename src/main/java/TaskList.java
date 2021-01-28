import java.util.ArrayList;

public class TaskList {
    ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("I have added the following task to your list:");
        System.out.println(task);
    }

    public void deleteTask(int index) {
        Task removed = tasks.remove(index);
        System.out.println("I have removed the following task from your list:");
        System.out.println(removed);
    }

    public void doneTask(int index) {
        Task task = tasks.get(index);
        task.checkTask();
        tasks.add(index, task);
        tasks.remove(index + 1);
        System.out.println("Congratulations on conquering this task:");
        System.out.println(task);
        System.out.println("You are one step closer to victory");
    }

    public void printTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(String.format("%d. ", i) + tasks.get(i - 1).toString());
        }
    }

    public int size() {
        return tasks.size();
    }

    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        return task;
    }
}
