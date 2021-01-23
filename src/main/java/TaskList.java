import java.util.*;

public class TaskList implements Iterable<Task> {
    private Vector<Task> taskArray;

    /**
     * Construct a new TaskList from existing tasks.
     * @param taskArray the array containing existing tasks
     */
    public TaskList(Collection<Task> taskArray) {
        this.taskArray = new Vector<>(taskArray);
    }

    /**
     * Add a new task to the end of the existing list.
     * @param newTask the task to be added
     */
    public void add(Task newTask) {
        taskArray.add(newTask);
    }

    /**
     * Remove a task by index.
     * @param index the index of the task to be removed
     * @return task removed from list
     * @throws InvalidTaskIndexException if the index is out of range (index < 0 || index >= size())
     */
    public Task remove(int index) throws InvalidTaskIndexException {
        try {
            return taskArray.remove(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Return the number of stored tasks.
     * @return the number of stored tasks
     */
    public int size() {
        return taskArray.size();
    }

    /**
     * Returns the task at the specified position in this TaskList.
     * @param index index of the element to return
     * @return Task at the specified index
     * @throws InvalidTaskIndexException if the index is out of range (index < 0 || index >= size())
     */
    public Task get(int index) throws InvalidTaskIndexException {
        try {
            return taskArray.get(index);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new InvalidTaskIndexException();
        }
    }

    /**
     * Returns the last task of the TaskList.
     * @return the last task of the TaskList, i.e., the component at index size() - 1
     */
    public Task last() {
        return taskArray.lastElement();
    }

    /**
     * Tests if this TaskList has no tasks.
     * @return true if and only if this TaskList has no tasks, that is, its size is zero; false otherwise.
     */
    public boolean isEmpty() {
        return taskArray.isEmpty();
    }

    /**
     * Returns an iterator over the tasks in this list in proper sequence.
     * The returned iterator is fail-fast.
     * @return an iterator over the elements in this list in proper sequence
     */
    public Iterator<Task> iterator() {
        return taskArray.iterator();
    }

    /**
     * Export the internal list of tasks as an unmodifiable list.
     * @return an unmodifiable view of the task list.
     */
    public List<Task> export() {
        return Collections.unmodifiableList(taskArray);
    }
}
