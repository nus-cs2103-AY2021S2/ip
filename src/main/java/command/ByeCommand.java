package command;
import mike.TaskList;

public class ByeCommand implements Command {

    public ByeCommand() { }

    @Override
    public boolean isExitCommand() {
        return true;
    }

    /**
     * Perform no operations on TaskList object
     * Accepts and returns TaskList due to compatibility
     * @param taskList tasklist provided
     * @return unmodified tasklist
     */
    @Override
    public TaskList runCommand(TaskList taskList) {
        return taskList;
    }

    /**
     * Returns goodbye message.
     * @return String of goodbye message
     */
    @Override
    public String getResponse() {
        return "Bye. Hope to see you again soon!";
    }
}
