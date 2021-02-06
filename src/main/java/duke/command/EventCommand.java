package duke.command;

import java.time.LocalDateTime;

import duke.bot.Storage;
import duke.exception.DukeCommandException;
import duke.exception.DukeException;
import duke.task.Event;

/** An executable command to create a task with start date and end date */
public class EventCommand extends Command {
    /** Description of an Event that is going to be created */
    private String desc = "";
    /** Starting date and time of an Event that is going to be created  */
    private LocalDateTime startDateTime;
    /** Ending date and time of an Event that is going to be created */
    private LocalDateTime endDateTime;

    /**
     * Constructor of an EventCommand
     *
     * @param desc Description of an Event that is going to be created
     * @param startDateTime Starting date and time of an Event that is going to be created
     * @param endDateTime Ending date and time of an Event that is going to be created
     */
    public EventCommand(String desc, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.desc = desc;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * Executes the Event command to create an Event task in the list and returns a response message
     *
     * @throws DukeCommandException if there is an issue with adding the Event into the list or saving the Event into
     * the hard disk
     */
    @Override
    public String execute() throws DukeCommandException {
        try {
            Event event = taskManager.addEvent(this.desc, this.startDateTime, this.endDateTime);
            Storage.save(taskManager.getTasks());
            return ui.constructAddMessage(event, taskManager.getTasksSize());
        } catch (DukeException e) {
            throw new DukeCommandException("event", desc, e.getMessage());
        }
    }
}
