package jeff;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> loadedTasks) {
        tasks = loadedTasks;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public int getNumTasks() {
        return tasks.size();
    }

    public String queryNumTasks() {
        return "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    public Task getTask(int i){
        return tasks.get(i);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public ArrayList<Task> findTask(String s) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getName().contains(s)) {
                foundTasks.add(t);
            }
        }
        return foundTasks;
    }
}
