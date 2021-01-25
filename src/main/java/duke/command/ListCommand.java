package duke.command;

import duke.TaskList;

public class ListCommand implements Command {

    private TaskList currentList;

    public boolean shouldExit() {
        return false;
    }

    public TaskList execute(TaskList taskList) {
        currentList = taskList;
        return taskList;
    }

    public String getResponse() {
        return currentList.toString();
    }
}
