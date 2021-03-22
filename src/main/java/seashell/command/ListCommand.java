package seashell.command;

import seashell.storage.SaveHandler;
import seashell.exception.SeashellException;
import seashell.TaskList;
import seashell.task.Task;

public class ListCommand implements Command {

    @Override
    public String execute(TaskList taskList, SaveHandler saveHandler) throws SeashellException {
        if (taskList.isEmpty()) {
            throw new SeashellException("OOPS!!! Task list is currently still empty!");
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i <= taskList.size(); i++) {
                Task t = taskList.get(i - 1);
                sb.append(i + ". " + t + "\n");
            }
            return sb.toString();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object other) {
        return this == other
                || other instanceof ListCommand;
    }
}
