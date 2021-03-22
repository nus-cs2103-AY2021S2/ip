package seashell.command;

import seashell.TaskList;
import seashell.exception.SeashellException;

public class ClearCommand implements Command {
    @Override
    public String execute(TaskList taskList) throws SeashellException {
        if (taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is already empty!");
        } else {
            taskList.clear();
            return "Task list has been cleared!";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof ClearCommand;
    }
}
