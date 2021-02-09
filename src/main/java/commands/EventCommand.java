package commands;

import datetime.ParseDateTime;
import tasklist.TaskList;
import tasks.Event;

public class EventCommand extends AddTaskWithTimeCommand {
    protected String timeArgDelimiter = "/at";

    public EventCommand(String commandBody) {
        super("event", commandBody);
        // todo this hardcoded string...
        // actually why do we need it, why not put it in toString or something
    }

    @Override
    public void run(TaskList taskList) {
        try {
            handleSplittingArgs();
            this.commandOutputMsg = taskList.addTask(new Event(
                    secondArg, parseInputStringToDateTime(thirdArg)));
        } catch (Exception e) {
            handleException(e);
        }

        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
