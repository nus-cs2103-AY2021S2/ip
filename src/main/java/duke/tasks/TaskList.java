package duke.tasks;

import duke.parser.DuplicateException;

import java.util.ArrayList;

/**
 * TaskList stores a list of Task Objects
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a task list containing the elements of the specified ArrayList.
     *
     * @param tasks The ArrayList whose elements are to be placed into the task list.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Inserts the specified task to the end of the task list.
     *
     * @param task Task to be added into the task list.
     * @return A new task list with the original tasks and the newly added task.
     */
    public TaskList add(Task task) {
        ArrayList<Task> newTaskList = new ArrayList<>();
        for (Task t: this.tasks) {
            newTaskList.add(t);
        }
        newTaskList.add(task);
        assert newTaskList.size() >= 1;
        return new TaskList(newTaskList);
    }

    /**
     * Returns the task at the specified position in the task list.
     *
     * @param index Index of the task to return.
     * @return The task at the specified position in the task list.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Removes the task at the specified position in the task list.
     * Shifts any subsequent tasks to the left (subtracts one from their indices).
     *
     * @param index The index of the task to be removed.
     * @return The new task list with all the original tasks expect the one that was deleted.
     */
    public TaskList remove(int index) {
        ArrayList<Task> newTaskList = this.tasks;
        newTaskList.remove(index);
        return new TaskList(newTaskList);
    }

    /**
     * Returns the task list in an ArrayList.
     *
     * @return An ArrayList containing all the tasks.
     */
    public ArrayList<Task> getList() {
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Checks if the task to be added already exists in the task list
     *
     * @param taskDescription Description of the task to be added into the task list.
     * @throws DuplicateException If the task to be added already exists in the task list.
     */
    public void hasDuplicate(String taskDescription) throws DuplicateException {
        for (Task t: this.tasks) {
            if (t.getDescription().equals(taskDescription)) {
                throw new DuplicateException("This task has already been recorded!\n"
                        + "Please enter another task!");
            }
        }
    }

    /**
     * Returns a well-formatted String representation of the tasks in the task list.
     *
     * @return A String representation of the tasks in the task list.
     */
    @Override
    public String toString() {
        String res = "Here are the tasks in your list:\n";
        int count = 1;
        for (Task t : this.tasks) {
            res += "     " + count + "." + t;
            count++;
        }
        return res;
    }
}
