package duke.tasks;

import java.util.ArrayList;

/**
 * List of tasks
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * TaskList constructor
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * TaskList constructor
     *
     * @param list ArrayList of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Gets task with given index
     *
     * @param index Given index
     * @return Task with give index
     */
    public Task getTask(int index) {
        return this.list.get(index);
    }

    /**
     * Gets list of tasks
     *
     * @return ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        return this.list;
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public int size() {
        return this.list.size();
    }

    /**
     * Marks specific task as done
     *
     * @param index Index of specific task
     * @return Task marked as done
     */
    public Task markCompleted(int index) {
        Task task = this.list.get(index);
        task.setCompleted();
        return task;
    }

    /**
     * Deletes specific task from TaskList
     *
     * @param index Index of specific task
     * @return Task that was deleted
     */
    public Task delete(int index) {
        Task task = this.list.get(index);
        this.list.remove(index);
        return task;
    }

    /**
     * Finds tasks with name same as some text
     *
     * @param text Given text
     * @return List of tasks
     */
    public TaskList find(String text) {
        TaskList result = new TaskList();
        for (Task curr : this.list) {
            if (curr.getName().contains(text)) {
                result.addTask(curr);
            }
        }
        return result;
    }
}
