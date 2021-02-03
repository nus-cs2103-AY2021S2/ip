package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    protected String time;

    public DeadlineTask(String description, String time) {
        super(description);
        this.time = time;
    }

    public String convertDateTime() {
        String[] dateTime = this.time.split("\\s+");
        String dateOfEvent = dateTime[0];
        String timeOfEvent = dateTime[1];
        LocalDate parsedDate = LocalDate.parse(dateOfEvent);
        return parsedDate.format(DateTimeFormatter.ofPattern("E, MMM d yyyy")) + " " + timeOfEvent;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateTime() + ")";
    }

}
