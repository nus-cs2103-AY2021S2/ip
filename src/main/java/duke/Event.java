package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an Task which is an Event.
 */
public class Event extends Task {
    protected LocalDate at;

    /**
     * Returns an Event class.
     *
     * @param description description of the task.
     * @param taskType the type of the task.
     * @param at the string representation of the date that the task will happen.
     * @throws DukeException if the string representation of the date is not in the correct format.
     */
    public Event(String description, TaskType taskType, String at) throws DukeException {
        super(description, taskType);
        try {
            this.at = LocalDate.parse(at);
        } catch (DateTimeException e) {
            throw new DukeException("Date is not in the correct format. Please put it in the form of yyyy-mm-dd.");
        }
    }

    private Event(String description, TaskType taskType, LocalDate at) {
        super(description, taskType);
        this.at = at;
    }

    @Override
    public Task copy() {
        Event taskCopy = new Event(this.description, this.taskType, this.at);
        taskCopy.isDone = this.isDone;
        return taskCopy;
    }

    @Override
    public String saveTaskString() {
        String delimiter = " ~ ";
        return super.saveTaskString() + delimiter + this.at;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (at: "
                + this.at.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}
