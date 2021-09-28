package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> taskList;

    /**
     * Constructs a TaskList with an empty task list.
     */
    public TaskList() {
        taskList = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with a given task list.
     *
     * @param taskList the given task list
     */
    public TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds new task in the task list.
     *
     * @param newTask the new task to be added
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
    }

    /**
     * Deletes a task in the task list.
     *
     * @param order the order of the task to be deleted
     * @return the deleted task
     * @throws DukeException no such order of task in the list
     */
    public Task deleteTask(int order) throws DukeException {
        if (order < 0 || order >= taskList.size()) {
            throw new DukeException("There's no task "
                    + (order + 1) + " in the list.");
        }
        return taskList.remove(order);
    }

    /**
     * Marks a task in the task list as done.
     *
     * @param order the order of the task to be marked as done
     * @throws DukeException no such task or that task had been finished
     */
    public void markAsDone(int order) throws DukeException {
        if (order < 0 || order >= taskList.size()) {
            throw new DukeException("There's no task "
                    + (order + 1) + " in the list.");
        } else if (taskList.get(order).isDone()) {
            throw new DukeException("This task had been finished before.");
        }
        taskList.get(order).markAsDone();
        assert taskList.get(order).isDone : "The markAsDone() is not functioning";
    }

    /**
     * Finds the tasks contain target keyword, and print them out.
     *
     * @param target target the target keyword
     * @return a list of tasks match the target word
     * @throws DukeException no task in the list or no task matches the name
     */
    public List<Task> findTask(String target) throws DukeException {
        if (taskList.size() == 0) {
            throw new DukeException("There's currently no task in the list.");
        }
        List<Task> targetTasks = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getName().contains(target)) {
                targetTasks.add(task);
            }
        }
        if (targetTasks.size() != 0) {
            return targetTasks;
        } else {
            throw new DukeException("There's currently no task name with \"" + target + "\".");
        }
    }

    /**
     * Gets the entire task list.
     *
     * @return the task list
     */
    public List<Task> getTaskList() {
        return taskList;
    }

    /**
     * Gets the task of the given order in task list.
     *
     * @param order the order of the task
     * @return the task of the order
     */
    public Task getTask(int order) {
        return taskList.get(order);
    }

    /**
     * Returns the size of the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return taskList.size();
    }

}
