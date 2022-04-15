package ekud.task;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Stream;

import ekud.common.exception.InvalidTaskIndexException;

/**
 * Iterable for containing any task type
 */
public class TaskList implements Iterable<Task> {
    private final Vector<Task> taskArray;
    private int doneCount;

    /**
     * Construct a new TaskList from existing tasks.
     *
     * @param taskArray The array containing existing tasks.
     */
    public TaskList(Collection<Task> taskArray) {
        this.taskArray = new Vector<>(taskArray);
        for (Task task : taskArray) {
            if (task.isDone) {
                ++doneCount;
            }
        }
    }

    /**
     * Construct a new TaskList with no existing tasks.
     */
    public TaskList() {
        taskArray = new Vector<>();
        doneCount = 0;
    }

    /**
     * Add a new task to the end of the existing list.
     *
     * @param newTask The task to be added.
     */
    public void add(Task newTask) {
        taskArray.add(newTask);
    }

    /**
     * Remove a task by index.
     *
     * @param index The index of the task to be removed.
     * @return Task removed from list.
     * @throws InvalidTaskIndexException If the index is out of range (index < 0 || index >= size()).
     */
    public Task remove(int index) throws InvalidTaskIndexException {
        try {
            Task deletedTask = taskArray.remove(index);
            if (deletedTask.isDone) {
                --doneCount;
            }
            return deletedTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Return the number of stored tasks.
     *
     * @return The number of stored tasks.
     */
    public int size() {
        return taskArray.size();
    }

    /**
     * Returns the task at the specified position in this TaskList.
     *
     * @param index Index of the element to return.
     * @return Task at the specified index.
     * @throws InvalidTaskIndexException if the index is out of range (index < 0 || index >= size()).
     */
    public Task get(int index) throws InvalidTaskIndexException {
        try {
            return taskArray.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Mark the task at index as done.
     *
     * @param index Index of the task to be marked as done.
     * @return The task marked as done.
     * @throws InvalidTaskIndexException if the index is out of range (index < 0 || index >= size()).
     */
    public Task markDone(int index) throws InvalidTaskIndexException {
        try {
            Task doneTask = taskArray.get(index);
            doneTask.markAsDone();
            ++doneCount;
            return doneTask;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Get the number of incomplete tasks.
     *
     * @return Number of incomplete tasks.
     */
    public int getIncompleteRemaining() {
        return taskArray.size() - doneCount;
    }

    /**
     * Returns the last task of the TaskList.
     *
     * @return The last task of the TaskList, i.e., the component at index size() - 1.
     */
    public Task last() {
        return taskArray.lastElement();
    }

    /**
     * Tests if this TaskList has no tasks.
     *
     * @return True if and only if this TaskList has no tasks, that is, its size is zero; false otherwise.
     */
    public boolean isEmpty() {
        return taskArray.isEmpty();
    }

    /**
     * Returns an iterator over the tasks in this list in proper sequence. The returned iterator is fail-fast.
     *
     * @return An iterator over the elements in this list in proper sequence.
     */
    public Iterator<Task> iterator() {
        return taskArray.iterator();
    }

    /**
     * Export the internal list of tasks as an unmodifiable list.
     *
     * @return An unmodifiable view of the task list.
     */
    public List<Task> export() {
        return Collections.unmodifiableList(taskArray);
    }

    /**
     * Expose the underlying container as a stream.
     *
     * @return Stream of the array containing the task list.
     */
    public Stream<Task> stream() {
        return taskArray.stream();
    }
}
