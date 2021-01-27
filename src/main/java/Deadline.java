import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

public class Deadline extends Task {
    private static final String OUTPUT_DATE_FORMAT = "MMM dd yyyy";
    private final LocalDate deadline;

    Deadline(String task, String deadline) throws DukeExceptionDeadline {
        super(task);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            throw new DukeExceptionDeadline("Wrong format of date." +
                    " The format should be yyyy-MM-dd");
        }
    }

    Deadline(String task, String deadline, boolean done) throws DukeExceptionDeadline {
        super(task, done);
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            this.deadline = LocalDate.parse(deadline, formatter);
        } catch (DateTimeParseException e) {
            throw new DukeExceptionDeadline("Wrong format of date. " +
                    "The format should be yyyy-mm-dd");
        }
    }

    Deadline(String task, LocalDate deadline, boolean done) {
        super(task, done);
        this.deadline = deadline;
    }

    @Override
    public Task finishTask() {
        return new Deadline(this.task, this.deadline,true);
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + this.deadline;
    }

    @Override
    public String toString() {
        String date = this.deadline.format(DateTimeFormatter.ofPattern(OUTPUT_DATE_FORMAT));
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
