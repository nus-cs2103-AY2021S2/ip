import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;
    private int listSize;

    /**
     * Initialises a newly created TaskList object
     * so that it represents an empty List of Task.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
        this.listSize = 0;
    }

    /**
     * Initialises a newly created TaskList object
     * so that it represents a List of Tasks
     * as given in the argument list.
     *
     * @param list a List of Task containing Task objects.
     */
    public TaskList(List<Task> list) {
        this.list = list;
        this.listSize = list.size();
    }

    /**
     * Adds the task argument into the task list.
     * Prints out a response to indicate successful adding of task.
     * Prints out the current number of tasks in the list.
     *
     * @param task a Task object to be added into the TaskList
     */
    Task addTask(Task task) {
        assert list != null : "List has not been instantiated";
        list.add(task);
        listSize++;

        return task;
    }

    /**
     * Removes the task of the given index from the task list.
     * Prints out a response to indicate successful removing of task.
     * Prints out the current number of tasks in the list.
     *
     * @param index the 1-based index of the Task object to be removed.
     */
    Task removeTask(int index) {
        assert list != null : "List has not been instantiated";

        Task temp = list.get(index - 1);
        list.remove(index - 1);
        listSize--;

        return temp;
    }

    /**
     * Marks the task of the given index as done.
     * Prints out a response to indicate successful marking of task.
     *
     * @param index the 1-based index of the Task object to be removed.
     */
    Task markTaskAsDone(int index) {
        assert list != null : "List has not been instantiated";
        assert !list.isEmpty() : "List has no tasks";

        Task temp = list.get(index - 1);
        temp.markAsDone();
        return temp;
    }

    /**
     * Returns the tasks in the list that matches the keyword argument.
     *
     * @param keyword the keyword to search for a Task.
     * @return a List of Task containing all matching tasks.
     */
    List<Task> searchTask(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();

        for (Task t : list) {
            if (t.name.contains(keyword)) {
                matchingTasks.add(t);
            }
        }
        return matchingTasks;
    }

    List<Task> getList() {
        assert list != null : "List has not been instantiated";
        return this.list;
    }

    int getListSize() {
        assert list != null : "List has not been instantiated";
        return this.listSize;
    }
}
