package mainclasses;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Deadline extends Task {



    public Deadline(String description, LocalDate deadline, LocalTime time) {
        super(description);
        this.date = deadline;
        this.type = TaskEnum.D;
        this.time = time;
    }

    @Override
    public String getDescription() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("K:mm a");
        return this.description + " (by: " + this.date.format(dateFormatter) + " " + this.time.format(timeFormatter) + ")";
    }
}
