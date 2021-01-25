package duke.command;

import duke.TaskList;

public class ByeCommand implements Command {

    public boolean shouldExit() {
        return true;
    }

    public TaskList execute(TaskList taskList) {
        return taskList;
    }

    public String getResponse() {
        return "Bye. Hope to see you again soon!";
    }

}
