package duke.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import duke.exceptions.DukeOutOfBoundsException;
import duke.tasks.Task;


/**
 * Wrapper class for a List of Tasks. Supports add and remove operations, as well as additional operations
 * to search and mark tasks as done. The tasks in the list are maintained in sorted order when tasks are added to it.
 */

public class TaskList implements Iterable<Task> {

    private static final String OUT_OF_BOUNDS_ERROR_MESSAGE = "Sorry, the index is out of Range ."
                    + "Try to specify a number from 1 to (the size of the list) ";

    private List<Task> listOfTasks;

    /**
     * Empty Constructor that wraps around an empty list.
     */
    public TaskList() {
        listOfTasks = new ArrayList<Task>();
    }

    /**
     * Constructor that wraps around the provided list of tasks.
     *
     * @param listOfTasks the task of lists to wrap around
     */
    public TaskList(List<Task> listOfTasks) {
        List<Task> copiedListOfTasks = new ArrayList<>(listOfTasks);
        Collections.sort(copiedListOfTasks);
        this.listOfTasks = copiedListOfTasks;
    }

    private void add(Task t) {
        listOfTasks.add(t);
    }

    /**
     * Adds the task to the end of the list, then sorts the TaskList.
     * This is to ensure list remains sorted after the insertion.
     *
     * @param t Task to be appended on the list.
     */
    public void insertIntoSortedPosition(Task t) {
        add(t);
        sort();
    }

    /**
     * Removes from the list by index ( 1 - based indexing ). Performs much like popping a task at a certain index.
     *
     * @param indexToDelete ( 1 -based indexing ) the index of the Task to delete.
     * @return Task that was deleted.
     */
    public Task pop(int indexToDelete) throws DukeOutOfBoundsException {
        Task taskToReturn = get(indexToDelete);
        listOfTasks.remove(indexToDelete - 1);
        return taskToReturn;
    }

    /**
     * gets a task at a certain index ( with 1- based indexing, so the first element starts from 0).
     *
     * @param index the index of the task to retrieve
     * @return a task at the index specified.
     */

    public Task get(int index) throws DukeOutOfBoundsException {
        boolean isOutOfBounds = (index < 1) || (index > listOfTasks.size());
        if (isOutOfBounds) {
            throw new DukeOutOfBoundsException(OUT_OF_BOUNDS_ERROR_MESSAGE);
        }
        return listOfTasks.get(index - 1);
    }

    /**
     * Marks a Task as done at certain index on the list. ( 1 -based indexing ).
     *
     * @param indexToMarkDone index of Task to mark
     * @throws DukeOutOfBoundsException thrown when there is no available task at that index.
     */

    public void markTaskDone(int indexToMarkDone) throws DukeOutOfBoundsException {
        Task task = get(indexToMarkDone);
        task.markAsDone();
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
     * Finds all tasks whose description contains the keyword string. The match is done case insensitively.
     *
     * @param keyword
     * @return
     */

    public TaskList filterByWord(String keyword) {
        List<Task> filteredListOfTasks = new ArrayList<>();
        for (Task currentTask : listOfTasks) {
            String taskString = currentTask.toString().toLowerCase();
            boolean isContainsKeyword = taskString.contains(keyword.toLowerCase());
            if (isContainsKeyword) {
                filteredListOfTasks.add(currentTask);
            }
        }
        return new TaskList(filteredListOfTasks);
    }

    private void sort() {
        Collections.sort(listOfTasks);
    }
}
