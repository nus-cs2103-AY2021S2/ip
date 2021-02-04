package task;

import java.util.ArrayList;

import ui.Ui;
/**
 * Class <code>TaskList</code> represents a list of <code>Task</code>object using <code>ArrayList</code>.
 * Contains <code>addTask</code> and <code>deleteTask</code> which can add/delete tasks in the list.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs a empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Find the specified <code>input</code> in the task list and
     * print out the task with matching description.
     *
     * @param input search parameter.
     */
    public String find(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasks) {
            String curr = task.getDetails();
            if (curr.contains(input)) {
                result.add(task);
            }
        }
        return Ui.printSearch(result);
    }

    /**
     * Returns a string that informs user of the total tally of the task in the task list.
     *
     * @return tally of task in task list.
     */
    public String getTally() {
        return "     Currently you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Appends the specified <code>task</code> into the end of the task list.
     *
     * @param task task being added.
     */
    public String addTask(Task task) {
        tasks.add(task);
        return Ui.ADD_MSG + task + "\n" + this.getTally();
    }

    /**
     * Mark the task at the specified <code>index</code> in the task list as complete.
     *
     * @param index index of task.
     */
    public String completeTask(int index) {
        Task task = tasks.get(index - 1);
        task.setComplete();
        return Ui.MARK_MSG + "      " + task;
    }

    /**
     * Delete the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task
     */

    public String deleteTask(int index) {
        String task = tasks.get(index - 1).toString();
        tasks.remove(index - 1);
        return Ui.DELETE_MSG + task + "\n \n" + getTally();
    }

    /**
     * Returns the number of task in the task list.
     *
     * @return number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task.
     * @return task specified by the index.
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    @Override
    public String toString() {
        return tasks.toString();
    }
}
