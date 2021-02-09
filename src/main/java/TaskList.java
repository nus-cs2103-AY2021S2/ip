import java.util.ArrayList;

/**
 * Simulates a list of tasks.
 */
public class TaskList {

    /** Lists the tasks in an ArrayList */
    private ArrayList<Task> taskArrayList;

    /**
     * Creates a TaskList object consisting of an empty list of tasks.
     */
    public TaskList() {
        taskArrayList = new ArrayList<>();
    }

    /**
     * Creates a TaskList object consisting of a lists of tasks.
     *
     * @param taskArrayList ArrayList of tasks that TaskList will contain.
     */
    public TaskList(ArrayList<Task> taskArrayList) {
        this.taskArrayList = taskArrayList;
    }

    /**
     * Returns number of tasks in the list.
     *
     * @return Integer denoting number of tasks in the list.
     */
    public int getSize() {
        return taskArrayList.size();
    }

    /**
     * Returns the task at the given index.
     *
     * @param index Index of the task of interest.
     * @return Task at the given index.
     */
    public Task getTask(int index) {
        assert index >= 0;
        return taskArrayList.get(index);
    }

    /**
     * Adds a task into the list.
     *
     * @param task Task to be added into the list.
     * @return New TaskList object with the added task.
     */
    public TaskList addTask(Task task) {
        taskArrayList.add(task);
        return new TaskList(taskArrayList);
    }

    /**
     * Deletes a task at the given index.
     *
     * @param index Index of the task to be deleted.
     * @return New TaskList object with the task deleted.
     */
    public TaskList deleteTask(int index) {
        assert index >= 0;
        taskArrayList.remove(index);
        return new TaskList(taskArrayList);
    }

    /**
     * Completes the task at the given index.
     *
     * @param index Index of the task to be completed.
     * @return New TaskList object with the task completed.
     */
    public TaskList completeTask(int index) {
        ArrayList<Task> output = taskArrayList;
        output.set(index, taskArrayList.get(index).completeTask());
        return new TaskList(output);
    }

    /**
     * Returns the entire list as a String.
     * Tasks are separated by a new line character "\n".
     *
     * @return String comprising the entire list of tasks.
     */
    public String getList() {

        String output = "";

        for (int i = 0; i < taskArrayList.size(); i++) {
            if (i == taskArrayList.size() - 1) {
                output = output + getTask(i).taskStatus();
            } else {
                output = output + getTask(i).taskStatus() + "\n";
            }
        }

        return output;
    }

    /**
     * Returns the ArrayList of tasks in this object.
     *
     * @return ArrayList of tasks.
     */
    public ArrayList<Task> getTaskArrayList() {
        return taskArrayList;
    }

    /**
     * Returns String containing statuses of tasks containing a keyword.
     *
     * @param keyword Keyword used to search tasks.
     * @return String containing statuses of tasks found.
     */
    public String search(String keyword) {

        ArrayList<Task> found = new ArrayList<>();

        for (Task t : taskArrayList) {
            if (t.getTask_details().contains(keyword)) {
                found.add(t);
            }
        }

        String output = "";

        for (int i = 0; i < found.size(); i++) {
            if (i == found.size() - 1) {
                output = output + found.get(i).taskStatus();
            } else {
                output = output + found.get(i).taskStatus() + "\n";
            }
        }

        return output;
    }
}
