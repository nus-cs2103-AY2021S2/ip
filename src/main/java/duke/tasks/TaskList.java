package duke.tasks;

import duke.exceptions.DukeException;

import java.util.ArrayList;

/**
 * class TaskList
 * @author Png Zheng Jie, Sebastian
 * Description: A class to represent the list of tasks
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructor: creates a new TaskList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructor: creates a TaskList from an existing ArrayList of Task
     * @param tasks the ArrayList of Task
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * getSize: retrieves the size of the TaskList
     * @return size of TaskList
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * getTaskList: retrieves the ArrayList of Task
     * @return ArrayList of Task
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * getTask: retrieves a Task in the TaskList
     * @param taskNum position of the Task in the TaskList
     * @return Task in the specified position
     */
    public static Task getTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            return tasks.get(taskNum - 1);
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }

    /**
     * addTask: adds a Task into the TaskList
     * @param task task to be added in the TaskList
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * deleteTask: deletes a Task from the TaskList
     * @param taskNum position of the Task to be deleted in the TaskList
     */
    public static void deleteTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            tasks.remove(taskNum - 1);
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }

    /**
     * completeTask: marks a Task in the TaskList as completed
     * @param taskNum position of the Task to be completed in the TaskList
     */
    public static void completeTask(int taskNum) throws DukeException {
        if (taskNum > 0 && taskNum <= tasks.size()) {
            Task taskToComplete = getTask(taskNum);
            taskToComplete.markAsDone();
        } else {
            throw new DukeException("☹ OOPS!!! Task " + taskNum + " is not in the task list!");
        }
    }
}
