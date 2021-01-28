import java.util.ArrayList;

/**
 * Represents all the tasks the user has input.
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Sets tasks ArrayList to the list of tasks that user has already input previous time Duke was used.
     *
     * @param prevTasks list of tasks that user has already input previous time Duke was used.
     */
    public static void setList(ArrayList<Task> prevTasks) {
        tasks = prevTasks;
    }

    /**
     * Returns an ArrayList of the tasks so far.
     *
     * @return ArrayList<Task> tasks so far.
     */
    public static ArrayList<Task> getList() {//prob with this is can change value as pass by ref
        return tasks;
    }

    /**
     * Return size of list of tasks.
     *
     * @return int size of list of tasks.
     */
    public static int listSize() {
        return tasks.size();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param task task to be added to list of tasks.
     */
    public static void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a String representation of all the tasks in the list of tasks.
     *
     * @return String of all the tasks in the list of tasks.
     */
    public static String printList() {
        return Ui.printList(tasks);
    }

    /**
     * Sets the task at index num to be complete and returns that task.
     *
     * @param num task number in the list that is completed.
     * @return Task task that has been completed.
     * @throws IndexOutOfBoundsException if num is not in the range of 0 to tasks list size or tasks list size is zero.
     */
    public static Task doneTask(int num) {
        if(tasks.size()==0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    "  No tasks to complete!\n" + Ui.lineGetter());
        } else if (num < 0 || num >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    " Enter 'done' followed by a number between " +
                    "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        tasks.get(num).doneTask();
        return tasks.get(num);
    }

    /**
     * Deletes a task at index num and returns that task.
     *
     * @param num task number in the list that is deleted.
     * @return Task task that has been deleted.
     * @throws IndexOutOfBoundsException if num is not in the range of 0 to tasks list size or tasks list size is zero.
     */
    public static Task deleteTask(int num) {
        if(tasks.size()==0) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    "  No tasks to delete!\n" + Ui.lineGetter());
        } else if (num < 0 || num >= tasks.size()) {
            throw new IndexOutOfBoundsException(Ui.lineGetter() +
                    " Enter 'delete' followed by a number between " +
                    "1 and " + tasks.size() + "\n" + Ui.lineGetter());
        }
        return tasks.remove(num);
    }
}
