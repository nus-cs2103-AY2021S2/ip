public class ExceptionCommand implements Command {

    private String ExceptionMessage;

    public ExceptionCommand(String ExceptionMessage) {
        this.ExceptionMessage = ExceptionMessage;
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        return taskList;
    }

    @Override
    public String getResponse() {
        return this.ExceptionMessage;
    }
}
