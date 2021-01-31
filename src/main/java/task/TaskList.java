package task;

import java.util.ArrayList;

import ui.Ui;
/**
 * Class <code>TaskList</code> represents a list of <code>Task</code>object using <code>ArrayList</code>.
 * Contains <code>addTask</code> and <code>deleteTask</code> which can add/delete tasks in the list.
 */
public class TaskList {

    protected ArrayList<Task> tasklist;

    /**
     * Constructs a empty task list.
     */
    public TaskList() {
        tasklist = new ArrayList<>();
    }

    /**
     * Find the specified <code>input</code> in the task list and
     * print out the task with matching description.
     *
     * @param input search parameter.
     */
    public void find(String input) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task : tasklist) {
            String curr = task.getDetails();
            if (curr.contains(input)) {
                result.add(task);
            }
        }
        Ui.printSearch(result);
    }

    /**
     * Returns a string that informs user of the total tally of the task in the task list.
     *
     * @return tally of task in task list.
     */
    public String getTally() {
        return "     Currently you have " + tasklist.size() + " tasks in the list.";
    }

    /**
     * Appends the specified <code>task</code> into the end of the task list.
     *
     * @param task task being added.
     */
    public void addTask(Task task) {
        tasklist.add(task);
        Ui.printBox(Ui.ADD_MSG + task + "\n \n" + getTally());
    }

    /**
     * Mark the task at the specified <code>index</code> in the task list as complete.
     *
     * @param index index of task.
     */
    public void completeTask(int index) {
        Task task = tasklist.get(index - 1);
        task.complete();
        Ui.printBox(Ui.MARK_MSG + "      " + task);
    }

    /**
     * Delete the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task
     */

    public void deleteTask(int index) {
        String task = tasklist.get(index - 1).toString();
        tasklist.remove(index - 1);
        Ui.printBox(Ui.DELETE_MSG + task + "\n \n" + getTally());
    }

    /**
     * Returns the number of task in the task list.
     *
     * @return number of tasks.
     */
    public int size() {
        return tasklist.size();
    }

    /**
     * Returns the task at the specified <code>index</code> in the task list.
     *
     * @param index index of task.
     * @return task specified by the index.
     */
    public Task get(int index) {
        return tasklist.get(index);
    }

    @Override
    public String toString() {
        return tasklist.toString();
    }
}
