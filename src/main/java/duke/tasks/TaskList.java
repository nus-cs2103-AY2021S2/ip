package duke.tasks;

import duke.exceptions.DukeExceptionIllegalArgument;

import java.util.ArrayList;

public class TaskList {

    ArrayList<Task> tasks; // composition

    /**
     * Constructor for TaskList.
     *
     * Creates empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructor for TaskList
     *
     * Initializes a TaskList based on an array of unparsed task strings.
     *
     * @param taskStrings List of task Strings.
     * @throws DukeExceptionIllegalArgument When parsing fails.
     */
    public TaskList(ArrayList<String> taskStrings) throws DukeExceptionIllegalArgument {
        tasks = new ArrayList<>(); // if fail, none imported
        boolean isImportSuccess = true;
        for (String s: taskStrings) {
            Task t = Task.parseFileString(s);
            tasks.add(t);
        }
    }

    /**
     * Sets a task within the list as done.
     *
     * Uses 1-based indexing.
     *
     * @param taskIndex Index of task in list.
     * @throws DukeExceptionIllegalArgument If the 1-based index is invalid.
     */
    public void setDone(int taskIndex) throws DukeExceptionIllegalArgument {
        getTask(taskIndex).setDone();
    }

    /**
     * Gets a task from the list using the index.
     *
     * Uses 1-based indexing.
     *
     * @param taskIndex Index of task in list.
     * @return Task.
     * @throws DukeExceptionIllegalArgument If the 1-based index is invalid.
     */
    public Task getTask(int taskIndex) throws DukeExceptionIllegalArgument {
        if (tasks.size() <= taskIndex || taskIndex < 0) {
            throw new DukeExceptionIllegalArgument("The task number must be valid.");
        }
        return tasks.get(taskIndex);
    }

    /**
     * Removes a task from the list using the index.
     *
     * Uses 1-based indexing.
     *
     * @param taskIndex Index of task in list.
     * @throws DukeExceptionIllegalArgument If the 1-based index is invalid.
     */
    public void deleteTask(int taskIndex) throws DukeExceptionIllegalArgument {
        if (tasks.size() <= taskIndex || taskIndex < 0) {
            throw new DukeExceptionIllegalArgument("The task number must be valid.");
        }
        tasks.remove(taskIndex);
    }

    /**
     * Removes all tasks from the list.
     */
    public void deleteAll() {
        tasks.clear();
    }

    /**
     * Appends a task to the list
     *
     * @param t Task.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns an ArrayList of parsed task strings.
     *
     * For writing into file.
     *
     * @return ArrayList of task Strings.
     */
    public ArrayList<String> asArrayList() {
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task t: tasks) {
            taskStrings.add(t.toFileString());
        }
        return taskStrings;
    }
}
