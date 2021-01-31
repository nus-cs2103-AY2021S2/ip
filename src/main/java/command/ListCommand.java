package command;
import mike.TaskList;

public class ListCommand implements Command {
    private boolean isExitCommand = false;
    private TaskList taskList;

    public ListCommand() { }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    /**
     * Store taskList provided to allow for listing of tasks
     * @param taskList TaskList object to be listed
     * @return unmodified TaskList object
     */
    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        return taskList;
    }

    /**
     * Wrap taskList.toString() with minor formatting
     * @return header for list before tasks
     */
    @Override
    public String getResponse() {
        return String.format(
                "Here are the tasks in your list:\n"
                        + "%s",
                this.taskList.toString());
    }
}
