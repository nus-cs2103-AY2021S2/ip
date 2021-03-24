package duke.task;

import java.time.LocalDate;

import duke.dukeexception.DukeException;

/**
 * Event is the main entity we'll be using to define a event task.
 * <p>
 * Please see the {@link Task} class for true identity
 *
 * @author Ni Jiaying
 */
public class Event extends Task {
    private static String eventFormat = "Format: event + name + /at + date[YYYY-MM-DD]";
    protected LocalDate at;

    /**
     * Class constructor specifying event name and duration.
     */
    public Event(String name, LocalDate at) {
        super(name);
        this.at = at;
    }

    /**
     * Class constructor specifying event name, duration and status.
     */
    public Event(String name, LocalDate at, boolean isDone) {
        super(name);
        this.at = at;
        this.isDone = isDone;
    }

    /**
     * Gets the date of a task.
     */
    public LocalDate getDate() {
        return at;
    }

    /**
     * Adds a event to taskList.
     *
     * @param count the current count of tasks in the taskList.
     * @throws DukeException If an input or output
     *                       exception occurred
     */
    @Override
    public String addTask(int count) throws DukeException {
        if (this.name.equals("event")) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        } else {
            return super.addTask(count);
        }
    }

    /**
     * Gets the type of a task.
     */
    public Character getType() {
        return 'E';
    }

    /**
     * Gets the format of an event.
     */
    public static String getEventFormat() {
        return eventFormat;
    }

    /**
     * Overrides toString method.
     *
     * @return the string representation of an event.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at.getMonth()
                + " " + at.getDayOfMonth() + " " + at.getYear() + ")";
    }
}

