package duke.task;

import duke.exceptions.DukeException;
import duke.ui.Ui;

import java.util.ArrayList;
import java.util.List;


/**
 * A class that store the task that user key in.
 */
public class Task {
    private static List<Task> taskList = new ArrayList<>();
    private final String taskName;
    private static int capacity = 0;
    private int index;
    private String isDone;
    private final String date;

    /**
     * Construct a task object with taskName attached and its index label in the taskList.
     * @param taskName name of the task.
     */
    Task(String taskName) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = " ";
        this.date = "";
        capacity++;
        add(this);
    }

    Task(String taskName, String done, boolean check) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = done;
        this.date = "";
        capacity++;
        add(this);
    }

    Task(String taskName, String date) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = " ";
        this.date = date;
        capacity++;
        add(this);
    }

    Task(String taskName, String date, String done) {
        this.taskName = taskName;
        this.index = capacity + 1;
        this.isDone = done;
        this.date = date;
        capacity++;
        add(this);
    }

    /**
     * Store the task key in by user in the taskList.
     *
     * @param t Task key in by user.
     */
    private static void add(Task t) {
        taskList.add(t);
    }

    /**
     * Mark a given task as done.
     *
     * @param i the index label of the Task.
     */
    public static void done(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.get(i - 1).isDone = "X";
            Ui.doneTask(t);
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    /**
     * Delete a task from the task list with the given index label.
     * @param i the index label of the task.
     */
    public static void delete(int i) {
        try {
            Task t = taskList.get(i - 1);
            taskList.remove(i - 1);
            Ui.deleteTask(t);
            for (Task task : taskList) {
                if (task.index > i) {
                    task.index--;
                }
            }
        } catch (IndexOutOfBoundsException e) {
            DukeException.taskErrorException();
        }
    }

    public static void clearAllTask() {
        taskList.clear();
    }

    /**
     * Get the name of the task.
     *
     * @return a String representation of the task name.
     */
    public String getTaskName() {
        return taskName;
    }

    /**
     * Get the taskList.
     *
     * @return the List of all tasks stored.
     */

    public static List<Task> getTaskList() {
        return Task.taskList;
    }

    /**
     * get the isDone status of the task.
     *
     * @return a String representation of the isDone status (X for done).
     */
    public String getDoneStatus() {
        return isDone;
    }

    /**
     * get the index label of the task.
     *
     * @return the int representation of the index label.
     */
    public int getIndex() {
        return index;
    }

    public int getType() {
        return 0;
    }

    public String getDate() {
        return date;
    }

    /**
     * get the current capacity of the taskList.
     *
     * @return the int representation of the capacity of the taskList.
     */
    public static int getCapacity() {
        return Task.capacity;
    }

    @Override
    public String toString() {
        return String.format("[%s] %d. %s", isDone, index, taskName);
    }
}