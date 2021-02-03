import java.util.ArrayList;

/**
 * TaskList keeps a list of all tasks.
 */
public class TaskList {

    /** List of tasks */
    private ArrayList<Task> list;

    /**
     * Initializes a newly created TaskList object with an empty list of tasks.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a task to the list of tasks.
     *
     * @param taskToAdd Task to be added to the list of tasks.
     */
    protected void addTask(Task taskToAdd) {
        this.list.add(taskToAdd);
    }

    /**
     * Removes a task from the list of tasks.
     *
     * @param positionOfTask Position of task to be removed in the list of tasks.
     */
    protected void removeTask(int positionOfTask) {
        this.list.remove(positionOfTask - 1);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return Integer representing the size of the list (number of tasks in the list).
     */
    protected int getSize() {
        return this.list.size();
    }

    /**
     * Returns the task in the given position from the list.
     *
     * @param positionOfTask Position of task to get in the list of tasks.
     * @return Task in the given position from the list.
     */
    protected Task getTask(int positionOfTask) {
        return this.list.get(positionOfTask - 1);
    }

    /**
     * Returns a string representation of the list of tasks in txt format.
     *
     * @return String representing the list of tasks in txt format.
     */
    protected String joinToTxt() {
        String joined = "";
        for (Task t : this.list) {
            joined += System.lineSeparator() + t.saveTask();
        }
        return joined;
    }

    /**
     * Returns list of all events.
     *
     * @return String representing the list of all events.
     */
    @Override
    public String toString() {
        String stringToReturn = "";
        for (int i = 1; i <= this.getSize(); i++) {
            if (i == this.getSize()) {
                stringToReturn += "\n" + i + ". " + this.getTask(i);
            } else {
                stringToReturn += "\n" + i + ". " + this.getTask(i) + "\n";
            }
        }
        return stringToReturn;
    }

}
