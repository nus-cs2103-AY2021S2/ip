package command;
import exception.MikeCommandExecutionException;
import mike.TaskList;
import task.Task;

public class DeleteCommand implements Command {
    private int taskIndexToDelete;
    private Task taskToDelete;
    private TaskList taskList;

    /**
     * Constructor for the delete command which takes in the index of the task to delete
     * @param taskIndexToDelete index of the task to delete
     */
    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Execute command and deletes the task in the provided taskList
     * @param taskList TaskList object to remove the task from
     * @return TaskList object after removing task from list
     * @throws MikeCommandExecutionException if task to be deleted does not exist in the taskList provided
     */
    @Override
    public TaskList runCommand(TaskList taskList) throws MikeCommandExecutionException {
        this.taskList = taskList;
        try {
            for (int i = 1; i <= this.taskList.getNumTasks(); i++) {
                if (i == taskIndexToDelete) {
                    this.taskToDelete = this.taskList.getNthTask(i);
                }
            }
            this.taskList.deleteNthTask(taskIndexToDelete);
            return this.taskList;
        } catch (NullPointerException e) {
            throw new MikeCommandExecutionException("DeleteCommand", "The task is not in the list");
        }

    }

    @Override
    public String getResponse() {
        return String.format(
                        "Noted. I've removed this task:  \n  "
                                + "%s\n"
                                + "Now you have %d tasks in the list.\n",
                this.taskToDelete.toString(), this.taskList.getNumTasks());
    }
}
