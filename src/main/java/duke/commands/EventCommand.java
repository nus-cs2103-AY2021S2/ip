package duke.commands;

import java.time.LocalDateTime;

import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.utils.Storage;

/**
 * Represents an event command which adds an event task to the task list.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";

    private String task;
    private LocalDateTime dateTime;

    /**
     * Creates a EventCommand object to store the event command input from the user.
     *
     * @param taskList the current list of Tasks.
     * @param storage the object in charge of writing to the local storage file.
     * @param task the String task input by the user.
     * @param dateTime the date and time associated with the task.
     */
    public EventCommand(TaskList taskList, Storage storage, String task, LocalDateTime dateTime) {
        super(taskList, storage);
        this.task = task;
        this.dateTime = dateTime;
    }

    /**
     * Adds an Event task with previously specified description and dateTime to taskList.
     *
     * @return confirmation message for added Deadline Task.
     */
    @Override
    public String execute() {
        Event event = new Event(this.task, this.dateTime);
        this.taskList.addTask(event);
        String successAddMsg = "Got it. I've added this task:\n" + event;
        return successAddMsg;
    }
}
