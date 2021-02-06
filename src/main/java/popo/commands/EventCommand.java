package popo.commands;

import static popo.utils.Messages.MESSAGE_ADDED_TASK;
import static popo.utils.Messages.MESSAGE_TASKLIST_SIZE_FORMAT;

import popo.tasks.EventTask;

/**
 * Creates an Event task.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    public static final String MESSAGE_USAGE = "Command Word: " + COMMAND_WORD + "\n"
            + "Description: Add an event task\n"
            + "Usage: event <task_description> /at <event_time>\n"
            + "Example: event Project Meeting /at NUS SoC COM1-0210";

    private final String taskName;
    private final String eventTime;

    /**
     * Creates an {@code EventCommand} object with the given task name and description of the event time.
     *
     * @param taskName Name of the task.
     * @param eventTime Description of the event time.
     */
    public EventCommand(String taskName, String eventTime) {
        this.taskName = taskName;
        this.eventTime = eventTime;
    }

    @Override
    public CommandResult execute() {
        assert taskList != null;
        EventTask task = new EventTask(taskName, eventTime);
        taskList.addTask(task);
        return new CommandResult(taskList, false,
                MESSAGE_ADDED_TASK + "\n",
                task.toString() + "\n",
                String.format(MESSAGE_TASKLIST_SIZE_FORMAT, taskList.size()));
    }
}
