package quackers.tasklist;

import quackers.QuackersException;
import quackers.task.Task;
import quackers.ui.Ui;

import java.util.ArrayList;

/**
 * Represents the task management capabilities of Quackers.
 */
public class TaskList {

    protected ArrayList<Task> taskList;

    /**
     * Constructs an empty TaskList object.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList object by populating a list of existing tasks.
     *
     * @param taskList List of tasks.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = new ArrayList<>(taskList);
    }

    /**
     * Retrieves the current size of the list.
     *
     * @return Size of list.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Retrieves the list of tasks.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getList() {
        return this.taskList;
    }

    /**
     * Retrieves the task at a given index.
     *
     * @param taskIndex Task index.
     * @return Task at the given index.
     */
    public Task get(int taskIndex) {
        return this.taskList.get(taskIndex);
    }

    /**
     * Retrieves the TaskList object.
     *
     * @return TaskList object.
     */
    public TaskList getTaskList() {
        return new TaskList(this.taskList);
    }

    /**
     * Retrieves the current size of a specific task in the list.
     *
     * @param cls Class of specific task.
     * @return Size of specific list of tasks.
     */
    public int getTaskCount(Class<? extends Task> cls) {
        int count = 0;
        for (Task task : this.taskList) {
            if (task.getClass() == cls) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Retrieves all the tasks with description match a given keyword.
     *
     * @param keyword Keyword to search.
     * @return List of tasks.
     */
    public TaskList findTasks(String keyword) {
        ArrayList<Task> temp = new ArrayList<Task>();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                temp.add(task);
            }
        }
        return new TaskList(temp);
    }

    /**
     * Adds task to list.
     *
     * @param task Task object.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }


    /**
     * Deletes task from list with a given index.
     *
     * @param taskIndex Task index.
     * @return Deleted task.
     * @throws QuackersException If index specified is invalid.
     */
    public Task deleteTask(int taskIndex) throws QuackersException {
        try {
            return this.taskList.remove(taskIndex - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new QuackersException(Ui.getDeleteTaskFailure());
        }
    }

    /**
     * Marks task from list as completed with a given index.
     *
     * @param taskIndex Task index.
     * @return Marked task.
     * @throws QuackersException If index specified is invalid.
     */
    public Task markDone(int taskIndex) throws QuackersException {
        try {
            Task task = this.taskList.get(taskIndex - 1);
            task.markAsDone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new QuackersException(Ui.getMarkDoneTaskFailure());
        }
    }

    /**
     * Marks task from list as incomplete with a given index.
     *
     * @param taskIndex Task index.
     * @return Marked task.
     * @throws QuackersException If index specified is invalid.
     */
    public Task markUndone(int taskIndex) throws QuackersException {
        try {
            Task task = this.taskList.get(taskIndex - 1);
            task.markAsUndone();
            return task;
        } catch (IndexOutOfBoundsException e) {
            throw new QuackersException(Ui.getMarkUndoneTaskFailure());
        }
    }
}
