package duke.commands;

import duke.tasks.EventTask;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private final String taskName;
    private final String eventTime;

    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute() {
        EventTask task = new EventTask(taskName, eventTime);
        taskList.addTask(task);
        return new CommandResult(MESSAGE_ADDED_TASK + "\n  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()), taskList);
    }
}
