package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list.
 */
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        taskList = new ArrayList<>();
    }

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
     */
    public void markAsDone(int order) throws DukeException {
        if (order < 0 || order >= taskList.size()) {
            throw new DukeException("There's no task "
                    + (order + 1) + " in the list.");
        } else if (taskList.get(order).isDone()) {
            throw new DukeException("This task has been finished before.");
        }
        taskList.get(order).markAsDone();
        assert taskList.get(order).done : "The markAsDone() is not functioning";
    }


    /**
     * Finds the tasks contain target keyword, and print them out.
     *
     * @param target the target keyword
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

    public List<Task> getTaskList() {
        return taskList;
    }

    public Task getTask(int order) {
        return taskList.get(order);
    }

    public int size() {
        return taskList.size();
    }

}
