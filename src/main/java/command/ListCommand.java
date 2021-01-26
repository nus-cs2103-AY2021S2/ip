package command;
import mike.TaskList;

public class ListCommand implements Command {
    private boolean isExitCommand = false;
    private TaskList taskList;

    public ListCommand() {
    }

    @Override
    public boolean isExitCommand() {
        return isExitCommand;
    }

    @Override
    public TaskList runCommand(TaskList taskList) {
        this.taskList = taskList;
        return taskList;
    }

    @Override
    public String getResponse() {
        return String.format(
                "Here are the tasks in your list:\n" +
                "%s",
                this.taskList.toString());
    }
}
