package Command;
import Mike.TaskList;

public class ByeCommand implements Command {

    public ByeCommand() {
    }

    @Override
    public boolean isExitCommand() {
        return true;
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
