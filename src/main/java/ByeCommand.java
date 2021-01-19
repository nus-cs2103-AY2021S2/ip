public class ByeCommand implements Command {

    private boolean isExitCommand = true;

    public ByeCommand() {
    }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
       return taskList;
    }

    @Override
    public String getResponse() {
        return "Bye. Hope to see you again soon!";
    }
}
