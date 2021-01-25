package duke.command;

import duke.tasklist.TaskList;

public class ListCommand extends Command{
    public ListCommand() {
    }

    @Override
    public String[] execute() {
        return TaskList.getAllTaskListInfo();
    }
}
