package seashell.command;

import seashell.SaveHandler;
import seashell.TaskList;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) {
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
