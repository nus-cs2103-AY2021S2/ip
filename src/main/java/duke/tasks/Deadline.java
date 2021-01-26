package duke.tasks;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    private final LocalDate date;
    private final LocalTime time;

    public Deadline(String description, LocalDate date, LocalTime time) {
        super(description, TaskType.DEADLINE);
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, LocalDate date, LocalTime time, Boolean completed) {
        super(description, TaskType.DEADLINE, completed);
        this.date = date;
        this.time = time;
    }

    public Deadline(String description, LocalDate date) {
        super(description, TaskType.DEADLINE);
        this.date = date;
        this.time = null;
    }

    public Deadline(String description, LocalDate date, Boolean completed) {
        super(description, TaskType.DEADLINE, completed);
        this.date = date;
        this.time = null;
    }

    public static Deadline parseDeadline(String description) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        } else if (!description.contains("/by")) {
            throw new DukeException("☹ OOPS!!! The description of a deadline must contain a time.");
        }
        String[] partitioned = description.split("/by");
        String desc = partitioned[0].strip();
        String[] datetime = partitioned[1].strip().split(" ");
        try {
            if (datetime.length == 2) {
                return new Deadline(desc, LocalDate.parse(datetime[0]), LocalTime.parse(datetime[1]));
            } else {
                return new Deadline(desc, LocalDate.parse(datetime[0]));
            }
        } catch (DateTimeParseException e) {
            throw new DukeException("☹ OOPS!!! The datetime description of a deadline must be either of the form" +
                    "'YYYY-MM-DD' or 'YYYY-MM-DD hh:mm'");
        }

    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder(super.toString());
        output.append(" (by: ");
        output.append(this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        if (this.time != null) {
            output.append(" ");
            output.append(this.time.format(DateTimeFormatter.ofPattern("h:mm a")));
        }
        output.append(")");
        return output.toString();
    }

    public String storageEntry() {
        StringBuilder output = new StringBuilder(super.storageEntry());
        output.append("|");
        output.append(this.date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        if (this.time != null) {
            output.append(" ");
            output.append(this.time.format(DateTimeFormatter.ofPattern("HH:mm")));
        }
        return output.toString();
    }
}
