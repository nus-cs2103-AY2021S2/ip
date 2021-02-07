package commands;

import tasklist.TaskList;
import tasks.Event;

public class EventCommand extends AddTaskWithTimeCommand {
    protected String timeArgDelimiter = "/at";

    protected EventCommand(String commandBody) {
        super("event", commandBody);
        // todo this hardcoded string...
        // actually why do we need it, why not put it in toString or something
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleSplittingArgs();
        } catch (Exception e) {
            handleException(e);
        }
        taskList.addTask(new Event(secondArg, thirdArg));
    }
}
