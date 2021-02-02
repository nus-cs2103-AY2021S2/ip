package duke.task;

import java.time.LocalDate;

import duke.dukeexception.DukeException;



public class Event extends Task {
    /** Event time */
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
     * Adds a event to taskList.
     *
     * @param count  the current count of tasks in the taskList.
     * @throws DukeException  If an input or output
     *                      exception occurred
     */
    @Override
    public void addTask(int count) throws DukeException {
        if (this.name.equals("event")) {
            throw new DukeException("      OOPS!!! The description of an event cannot be empty.");
        } else {
            super.addTask(count);
        }
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

