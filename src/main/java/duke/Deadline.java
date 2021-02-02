package duke;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Returns a Deadline Task.
     *
     * @param description description of the task.
     * @param taskType the type of the task.
     * @param by the date that the task has to be done by.
     * @throws DukeException
     */
    public Deadline(String description, TaskType taskType, String by) throws DukeException {
        super(description, taskType);
        try {
            this.by = LocalDate.parse(by);
        } catch (DateTimeException e) {
            throw new DukeException("Date is not in the correct format");
        }
    }

    @Override
    public String saveTaskString() {
        String delimiter = " ~ ";
        return super.saveTaskString() + delimiter + this.by;
    }

    @Override
    public String toString() {
        return super.toString()
                + " (by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
