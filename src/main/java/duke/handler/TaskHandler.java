package duke.handler;

import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.tasks.TaskList;

/**
 * Abstract TaskHandler class
 */
abstract class TaskHandler implements CommandHandler {
    protected Task toAdd;
    protected String response;

    public TaskHandler(Task toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList taskList) {
        taskList.addTask(toAdd);
        response = taskRespond(toAdd, taskList);
        storage.addTask(toAdd);
        return response;
    }

    /**
     * Returns the string for the task handled.
     * @param toAdd Task to be handled.
     * @param taskList The list of tasks.
     * @return The string response.
     */
    public String taskRespond(Task toAdd, TaskList taskList) {
        int numOfTasks = taskList.getNumOfTasks();
        return "Got it. I've added this task:\n"
                + " " + toAdd + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n";
    }
}
