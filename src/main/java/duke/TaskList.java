package duke;

import duke.Task;
import java.util.ArrayList;

/**
 * TaskList class to store the list of tasks
 */
public class TaskList {
    public ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Constructor for TaskList class
     * Initializes a TaskList object with list of tasks specified
     * @param list list of tasks
     */
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Adds a task to list
     * @param task task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from list
     * @param num number of task to be deleted
     */
    public Task deleteTask(int num) {
        return list.remove(num-1);
    }

    /**
     * Checks a task as done
     * @param task task to be checked as done
     */
    public void checkAsDone(Task task) {
        task.done();
    }

}
