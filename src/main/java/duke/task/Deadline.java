package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime localDate;

    public Deadline(String input, String date) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
    }

    public Deadline(String input, String date, int done) {
        super(input);
        this.localDate = new ParseDates().parseString(date);
        if (done == 1) {
            this.doTask();
        }
    }

    public static Deadline createDeadline(String input) {
        int indexOfBy = input.lastIndexOf("/by ");
        String date = input.substring(indexOfBy + 3).trim();
        return new Deadline(input.substring(0, indexOfBy).strip(), date);
    }

    @Override
    public String saveTask() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HHmm");
        return "D" + super.saveTask() + " | " + localDate.format(dateTimeFormatter);
    }

    @Override

    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + localDate.format(dateTimeFormatter) + ")";
    }
}
