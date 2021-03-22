package seashell.command;

import seashell.storage.SaveHandler;
import javafx.application.Platform;
import seashell.TaskList;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskListObj, SaveHandler saveHandler) {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
