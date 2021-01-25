package duke.command;

import duke.task.TaskList;

public class ByeCmd extends Command {
    private static final String byeMsg = "Bye. Hope to see you again soon!\n";

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(TaskList lst) {
        return byeMsg;
    }
}
