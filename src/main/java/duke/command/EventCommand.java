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
    private LocalDateTime start;
    /** Ending date and time of an Event that is going to be created */
    private LocalDateTime end;

    /**
     * Constructor of an EventCommand
     *
     * @param desc Description of an Event that is going to be created
     * @param start Starting date and time of an Event that is going to be created
     * @param end Ending date and time of an Event that is going to be created
     */
    public EventCommand(String desc, LocalDateTime start, LocalDateTime end) {
        this.desc = desc;
        this.start = start;
        this.end = end;
    }

    /**
     * Executes the Event command to create an Event task in the list
     *
     * @throws DukeCommandException if there is an issue with adding the Event into the list or saving the Event into
     * the hard disk
     */
    @Override
    public String execute() throws DukeCommandException {
        try {
            Event event = taskManager.addEvent(this.desc, this.start, this.end);
            Storage.saveTasks(taskManager.getTasks());
            return ui.constructAddMsg(event, taskManager.getTasksSize());
        } catch (DukeException e) {
            throw new DukeCommandException("event", desc, e.getMessage());
        }
    }
}
