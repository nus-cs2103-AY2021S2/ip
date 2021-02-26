package data;

import data.task.ITask;
import data.task.TaskList;
import storage.TaskManager;

import java.util.List;
public interface IDuke {

    /**
     * Prints greeting message.
     */
    public String greeting();


    /**
     *
     * @param command User command
     * @return data.IDuke object
     */
    String handleCommand(String command);

    /**
     *
     * @param task a task
     * IDuke object with the adding task
     */
    void addTask(ITask task);

    /**
     * Prints the task list
     */

    ITask getTask(int id);

    /**
     *
     * @return number of tasks in the list
     */
    int numOfTasks();

    TaskManager getTaskManager();

    TaskList  getTasks();

    String getResponse(String input);

}
