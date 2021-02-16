package duke;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

public class TaskList {
    private List<Task> tasks;
    private Storage storage;

    /**
     * TaskList provides an abstraction for a list of Tasks.
     * Additionally, it provides other helpful Task-related functions which would be a hassle without the abstraction.
     * @param storage Storage of the tasks' .txt file
     */
    TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new ArrayList<Task>();
    }

    /**
     * This method overloads TaskList(Storage storage), with an additional List&lt;Task&gt; parameter.
     * It is used over TaskList(Storage storage) when the user already has existing tasks.
     * @param tasks List of Tasks to insert into TaskList
     * @param storage Storage of the tasks' .txt file
     */
    public TaskList(List<Task> tasks, Storage storage) {
        this(storage);
        this.tasks = tasks;
    }

    /**
     * Returns List of Tasks, avoiding access to tasks variable directly.
     * @return List of Tasks
     */
    public List<Task> get() {
        return this.tasks;
    }

    /**
     * Adds a new task to List of Tasks
     * @param task Task to be added
     */
    public void add(Task task) {
        tasks.add(task);
        storage.writeToFile(this);
    }

    /**
     * Deletes a task from the List of Tasks
     * @param task Task to be deleted
     */
    public void delete(Task task) {
        tasks.remove(task);
        storage.writeToFile(this);
    }

    /**
     * Deletes all task from List of Tasks
     */
    public void deleteAll() {
        tasks.clear();
        storage.writeToFile(this);
    }

    /**
     * Retrieve task from List of Task based on index (0-based)
     * @param i index of Task
     * @return Task with index i
     */
    public Task find(int i) {
        return tasks.get(i);
    }

    /**
     * Retrieves all tasks relating to the given criteria (search)
     * @param criteria keyword given to search task
     * @return Tasks relating to the given keyworrd
     */
    public TaskList findAll(String criteria) {
        List<Task> tempTask = new ArrayList<Task>();
        for (Task task: tasks) {
            if (task.getName().contains(criteria)) {
                tempTask.add(task);
            }
        }
        return new TaskList(tempTask, this.storage);
    }

    /**
     * Filters the current list of Tasks and returns a new list of tasks with the specified date.
     * @param date Filter date in LocalDate
     * @return List of Tasks with specified date
     */
    public List<Task> getByDate(LocalDate date) {
        List<Task> tempTask = new ArrayList<Task>();
        for (Task task: tasks) {
            if (task.getDate() != null && task.getDate().isEqual(date)) {
                tempTask.add(task);
            }
        }
        return tempTask;
    }

    /**
     * Returns the number of tasks in the list
     * @return number of tasks in list
     */
    public int size() {
        return this.tasks.size();
    }

    @Override
    public String toString() {
        String tasksContent = "";
        for (int i = 0; i < tasks.size(); i++) {
            tasksContent += (i + 1 + ". " + tasks.get(i) + "\n");
        }
        return tasksContent.trim();
    }
}
