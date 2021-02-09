package duke;

import java.util.ArrayList;

/**
 *  Contains methods in TaskList object
 */

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks){
        this.tasks = tasks;
    }

    /**
     * Adds task into task list.
     *
     * @param task Task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Removes task at specified index from task list.
     *
     * @param index Index of task to be removed.
     * @return Task removed.
     */
    public Task removeTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    /**
     * Marks task at specified index as done.
     *
     * @param index Index of task to be marked as done.
     */
    public void doneTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        tasks.add(index, task);
        tasks.remove(index + 1);
    }

    public ArrayList<Task> getList(){
        return this.tasks;
    }

    /**
     * Returns number of tasks in task list.
     *
     * @return Number of tasks in task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task at a specified index.
     *
     * @param index Index of task to return.
     * @return Task.
     */
    public Task getTask(int index) {
        Task task = this.tasks.get(index);
        return task;
    }

    /**
     * Represents contents of TaskList as a string
     * @return String of TaskList
     */
    public String toString(){
        String output = "";
        for(int i = 0; i< tasks.size(); i++){
            if(i != tasks.size() - 1){
                output += " " + tasks.get(i).toString() + "\n";
            } else {
                output += " " + tasks.get(i).toString();
            }
        }
        return output;
    }
}
