package duke;

import java.util.ArrayList;
import java.util.List;

/**
 * Handle all actions regarding tasks.
 */
public class TaskList {
    private List<Task> lst;

    /**
     * Construct a list of tasks.
     */
    TaskList() {
        this.lst = new ArrayList<>();
    }

    /**
     * Overloaded constructor, used when reading a TaskList from disk.
     *
     * @param lst the TaskList stored in disk
     */
    TaskList(List<Task> lst) {
        this.lst = lst;
    }

    /**
     * Retrieves a Task from the TaskList at a specified index.
     *
     * @param index index of the task to be retrieved
     * @return the retrieved task
     */
    public Task get(int index) {
        assert index < lst.size();
        return this.lst.get(index);
    }

    /**
     * Returns the size of the TaskList.
     *
     * @return the size of the TaskList
     */
    public int size() {
        return this.lst.size();
    }

    /**
     * Inserts a task into the TaskList.
     *
     * @param task the task to be added.
     */
    public void add(Task task) {
        this.lst.add(task);
    }

    /**
     * Removes a task at a given index.
     *
     * @param index the specified index
     * @return the task removed
     */
    public Task delete(int index) {
        assert index < lst.size();
        return this.lst.remove(index);
    }

    /**
     * Empties the TaskList
     */
    public void empty() {
        for (int i = lst.size() - 1; i >= 0; i--) {
            delete(i);
        }
    }

    /**
     * Returns every tasks that match several keywords.
     *
     * @param keywords the keywords to be searched.
     * @return a list of tasks containing the keyword.
     */
    public TaskList find(String ... keywords) {
        TaskList tasksWithKeyword = new TaskList();
        for (Task task : lst) {
            boolean hasKey = true;
            for (String keyword : keywords) {
                if (!task.getTask().contains(keyword)) {
                    hasKey = false;
                    break;
                }
            }
            if (hasKey) {
                tasksWithKeyword.add(task);
            }
        }
        return tasksWithKeyword;
    }

    /**
     * Returns String representation of the tasks in TaskList.
     * @return strings representing tasks
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");

        for (int i = 0; i < lst.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, lst.get(i)));
        }
        return sb.toString().trim();
    }
}
