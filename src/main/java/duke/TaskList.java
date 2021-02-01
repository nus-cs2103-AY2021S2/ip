package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Wrapper class for a List of Tasks. Supports add and remove operations, as well as additional operations
 * to search and mark tasks as done.
 */

public class TaskList implements Iterable<Task> {

    private static List<Task> listOfTasks;

    /**
     * Empty Constructor that wraps around an empty list.
     */
    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    /**
     * Constructor with 1 input.
     *
     * @param listOfTasks the task of lists to wrap around
     */
    public TaskList(List<Task> listOfTasks) {
        TaskList.listOfTasks = listOfTasks;
    }

    /**
     * Adds the task to the end of the list, much like append.
     *
     * @param t Task to be appended on the list.
     */
    public void add(Task t) {
        listOfTasks.add(t);
    }

    /**
     * Removes from the list by index ( 1 - based indexing ). Performs much like popping a task at a certain index.
     *
     * @param indexToDelete ( 1 -based indexing ) the index of the Task to delete.
     * @return Task that was deleted.
     */
    public Task delete(int indexToDelete) {
        Task taskToReturn = listOfTasks.get(indexToDelete - 1);
        listOfTasks.remove(indexToDelete - 1);
        return taskToReturn;
    }

    /**
     * Marks a Task as done at certain index on the list. ( 1 -based indexing _
     *
     * @param indexToMarkDone index of Task to mark
     * @return Task that is marked
     */

    public Task markTaskDone(int indexToMarkDone) {
        Task task = listOfTasks.get(indexToMarkDone - 1);
        task.markAsDone();
        return task;
    }

    /**
     * Returns the length of the list.
     *
     * @return length of the list
     */

    public int size() {
        return listOfTasks.size();
    }

    /**
     * Returns iterator to iterate through the Tasks.
     *
     * @return iterator to the List of Tasks.
     */

    @Override
    public Iterator<Task> iterator() {
        return listOfTasks.iterator();
    }

    /**
     * Finds all tasks whose description contains the keyword. The match is done case insensitively.
     *
     * @param keyword
     * @return
     */

    public TaskList filterByWord(String keyword) {
        List<Task> filteredListOfTasks = new ArrayList<>();
        for (Task currentTask : listOfTasks) {
            String description = currentTask.getDescription().toLowerCase();
            boolean isContainsKeyword = description.contains(keyword.toLowerCase());
            if (isContainsKeyword) {
                filteredListOfTasks.add(currentTask);
            }
        }
        return new TaskList(filteredListOfTasks);
    }
}
