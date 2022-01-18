package duke.data;

import duke.data.task.ITask;
import duke.data.task.TaskList;
import duke.storage.Storage;

public interface IDuke {
    /**
     * Prints and returns greeting message.
     */
    String greet();

    /**
     * Saves a task in Duke.
     *
     * @param task Task to be saved.
     */
    void storeTask(ITask task);

    /**
     * Returns a specific task from the list.
     *
     * @return The specified task.
     */
    ITask getTask(int id);

    /**
     * Returns a list of all the tasks.
     *
     * @return The specified task
     */
    TaskList getTasks();

    /**
     * Gets the number of tasks in the list.
     *
     * @return Number of tasks currently in the list.
     */
    int getNumTask();

    /**
     * Handles a given command and returns the resulting message.
     *
     * @param command A user command.
     * @return Response string from Duke.
     */
    String handleCommand(String command);

    Storage getStorage();

    String getResponse(String input);
}
