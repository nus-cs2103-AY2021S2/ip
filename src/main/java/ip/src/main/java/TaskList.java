package ip.src.main.java;
import java.util.ArrayList;
import java.util.List;

/**
 * TaskList stores and manipulate the tasks in a List.
 *
 */
public class TaskList {
    protected List<Task> list;

    TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Adds a Task to the list.
     *
     * @param task New Task to be added to list.
     */
    public void addTask(Task task) {
        this.list.add(task);
    }

    /**
     * Checks if list is empty.
     *
     * @return Boolean to indicate if list is empty.
     */
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    /**
     * Prints the tasks in the bot's tasklist.
     *
     * @return A string representation of the list of the tasks in the tasklist.
     */
    public String printTasks() {
        int counter = 1;
        String output = "";
        for (Task element : this.list) {
            output += String.valueOf(counter) + ". " + element + "\n";
            counter++;
        }
        output += "Now you have " + String.valueOf(this.list.size()) + " tasks in the list.";
        return output;
    }

    /**
     * Prints the tasks that contain the keyword/s from the bot's tasklist.
     *
     * @return A string representation of the list of matching tasks.
     */
    public String printMatchingTasks() {
        int counter = 1;
        String output = "";
        for (Task element : this.list) {
            output += String.valueOf(counter) + ". " + element + "\n";
            counter++;
        }
        output += "You have " + String.valueOf(this.list.size()) + " matching tasks from the list!";
        return output;
    }

    /**
     * Prints the edited bot's tasklist.
     *
     * @return A string representation of the newly edited tasks in the tasklist.
     */
    public String printEditedTasks() {
        int counter = 1;
        String output = "";
        for (Task element : this.list) {
            output += String.valueOf(counter) + ". " + element + "\n";
            counter++;
        }
        return output;
    }

    /**
     * Gets the task at a specified position in the list.
     *
     * @param id The position of the task to be retrieved in the list.
     * @return A task in the list.
     */
    public Task getTask(int id) {
        return this.list.get(id);
    }

    /**
     * Removes a task at a specified position in the list.
     *
     * @param id The position of the task to be removed from the list.
     */
    public void remove(int id) {
        this.list.remove(id);
    }

    /**
     * Returns size of the Task list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Gets the matching tasks that contain the keyword/s.
     *
     * @param keyword Keyword/s given by user.
     * @return A tasklist of tasks that contain the keyword/s.
     */
    public TaskList findTasks(String keyword) {
        TaskList taskListWithMatches = new TaskList();

        for (Task task: this.list) {
            if (task.isMatch(keyword)) {
                taskListWithMatches.addTask(task);
            }
        }
        return taskListWithMatches;
    }

    protected void editTask(Task newTask , int id) {
        this.list.set(id , newTask);
    }
}
