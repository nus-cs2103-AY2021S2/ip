package commands;

import tasklist.TaskList;
import tasks.Event;

public class EventCommand extends AddTaskWithTimeCommand {

    public EventCommand(String commandBody) {
        super("event", commandBody);
        // todo this hardcoded string...
        // actually why do we need it, why not put it in toString or something
        this.timeArgDelimiter = "/at";
        this.missingThirdArgErrMsg = "missing date/time argument";
    }

    @Override
    public void run(TaskList taskList) {
        // w6 fix indenting + what to do with thirdArg
        try {
            this.parseCommandBody();
            this.commandOutputMsg = taskList.addTask(
                    new Event(secondArg, parseToKiwiDateTime(thirdArg)));
        } catch (Exception e) {
            handleException(e);
        }
        assert !this.commandOutputMsg.isEmpty() : "empty command output in " + this.commandName;
    }
}
