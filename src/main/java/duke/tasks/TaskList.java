package duke.tasks;

import java.util.ArrayList;

import duke.exceptions.DukeExceptionIllegalArgument;
import duke.parser.filestring.GetTaskFileStringParser;

public class TaskList {

    private final ArrayList<Task> tasks; // composition

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
     * Initializes a TaskList based on an array of un-parsed task strings.
     *
     * @param taskStrings List of task Strings.
     * @throws DukeExceptionIllegalArgument When parsing fails.
     */
    public static TaskList fromFileStrings(ArrayList<String> taskStrings) throws DukeExceptionIllegalArgument {
        TaskList tasklist = new TaskList();
        GetTaskFileStringParser parserFactory = new GetTaskFileStringParser();
        for (String s: taskStrings) {
            Task t = parserFactory.getFileStringParser(s).fromFileString(s);
            tasklist.addTask(t);
        }
        return tasklist;
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
     * Gets a task from the list using the index, in an unsafe manner.
     *
     * Uses 1-based indexing. Input index must already be validated.
     *
     * @param taskIndex Index of task in list.
     * @return Task.
     */
    public Task getTaskUnsafe(int taskIndex) {
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
     * @throws DukeExceptionIllegalArgument When task type is not supported for conversion.
     */
    public ArrayList<String> asArrayList() throws DukeExceptionIllegalArgument {
        GetTaskFileStringParser parserFactory = new GetTaskFileStringParser();
        ArrayList<String> taskStrings = new ArrayList<>();
        for (Task t: tasks) {
            String taskString = parserFactory.getFileStringParser(t).toFileString(t);
            taskStrings.add(taskString);
        }
        return taskStrings;
    }

    /**
     * Returns left-spaced padding required to align all tasks in tasklist, given 1-based index.
     *
     * @param index 1-based index
     * @return String left padding
     */
    public String getLeftPadding(int index) {
        int totalNumDigits = (int) Math.floor(Math.log10(tasks.size())) + 1;
        int currNumDigits = (int) Math.floor(Math.log10(index)) + 1;
        return new String(new char[totalNumDigits - currNumDigits]).replace('\0', ' ');
    }
}
