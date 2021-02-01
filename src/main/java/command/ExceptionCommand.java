package command;

import mike.TaskList;

public class ExceptionCommand implements Command{
    private String errMsg;

    public ExceptionCommand(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public boolean isExitCommand() {
        return false;
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
        return errMsg;
    }
}
