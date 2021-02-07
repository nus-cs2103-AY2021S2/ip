package Duke;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.InvalidDescriptionException;
import Duke.Tasks.Task;

import java.util.ArrayList;


/**
 * Contains the task list which is an ArrayList of <code>Tasks</code>
 */
public class TaskList {

    ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Removes the <code>Duke.Tasks.Task</code> by specifying the index from the ArrayList of <code>Tasks</code>
     *
     * @param taskIndex indicates the task index in the ArrayList <code>Tasks</code>
     * @throws InvalidDescriptionException is thrown when there is an error related to an invalid description
     */
    public void deleteTask(int taskIndex) throws InvalidDescriptionException {
        assert taskIndex >= 0;

        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.remove(taskIndex);
    }

    /**
     * Marks the <code>Duke.Tasks.Task</code> as done
     *
     * @param taskIndex indicates the task index in the ArrayList of <code>Tasks</code>
     * @throws DukeException is thrown when there is an error related to duke
     */
    public void markTask(int taskIndex) throws DukeException {
        assert taskIndex >= 0;

        if (taskIndex >= tasks.size()) {
            throw new InvalidDescriptionException("Sorry, I am unable to process what was written after the command...");
        }

        tasks.get(taskIndex).markAsDone();
    }

    /**
     * Gets the ArrayList of <code>Tasks</code>
     *
     * @return Returns the stored ArrayList
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Adds the task to the ArrayList
     *
     * @param task represents the <code>Duke.Tasks.Task</code> to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Finds <code>Duke.Tasks.Task</code> in the ArrayList that contains the matching word
     *
     * @param keyWord indicates the word to find
     * @return an ArrayList that contains the <code>Tasks</code> that has the keyword
     */
    public ArrayList<Task> findTask(String keyWord) {
        ArrayList<Task> matchingWords = new ArrayList<>();

        for (Task t : tasks) {
            if (t.toString().contains(keyWord)) {
                matchingWords.add(t);
            }
        }

        return matchingWords;
    }
}
