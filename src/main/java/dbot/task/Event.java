package dbot.task;

import dbot.task.Task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * An implementation of the duke.task.Task class that represents duke.commands.Event Tasks.
 * <p>
 * duke.commands.Event tasks are tasks that take a description, track whether they are done or not,
 * and take an /at parameter, specifying the Date & Location of the duke.commands.Event.
 * <p>
 * The duke.commands.Event class is visually represented with the prefix: [E]
 *
 * @author Douglas Wei Jing Allwood
 * @author douglas_allwood@u.nus.edu
 */
public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    /**
     * Returns the exact String required to Construct this Task.
     *
     * @return A String that can be used to construct an equivalent Task.
     */
    @Override
    public String getFullDescription() {
        return getDescription() + " /at " + at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
