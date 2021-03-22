package seashell.command;

import javafx.application.Platform;
import seashell.TaskList;

public class ExitCommand implements Command {

    @Override
    public String execute(TaskList taskList) {
        Platform.exit();
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof ExitCommand;
    }
}
