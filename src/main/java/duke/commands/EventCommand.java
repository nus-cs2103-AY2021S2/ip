package duke.commands;

import static duke.utils.Messages.MESSAGE_ADDED_TASK;
import static duke.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import duke.tasks.EventTask;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add an event task\n"
            + "Usage: event <task_description> /at <event_time>\n"
            + "Example: event Project Meeting /at NUS SoC COM1-0210";

    private final String TASK_NAME;
    private final String EVENT_TIME;

    /**
     * Creates an {@code EventCommand} object with the given task name and description of the event time.
     *
     * @param taskName Name of the task.
     * @param eventTime Description of the event time.
     */
    public EventCommand(String taskName, String eventTime) {
        TASK_NAME = taskName;
        EVENT_TIME = eventTime;
    }

    @Override
    public CommandResult execute() {
        EventTask task = new EventTask(TASK_NAME, EVENT_TIME);
        taskList.addTask(task);
        String messageForUser = MESSAGE_ADDED_TASK + "\n"
                + "  " + task.toString() + "\n"
                + String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size());
        return new CommandResult(messageForUser, taskList, false);
    }
}
