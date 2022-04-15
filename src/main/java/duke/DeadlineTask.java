package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * A deadline task is a task which has a date it has to be done by
 */
public class DeadlineTask extends Task {

    protected String time;

    /**
     * Constructor for a deadline task
     * @param description Describes what the task is
     * @param time The date that the task have to be completed by
     */
    public DeadlineTask(String description, String time) {
        super(description);
        this.time = time;
    }

    /**
     * Converts the date in the form of mm-dd-yyyy to a more natural and readable form
     * @return The formatted date and time in the form of E, MMM d yyyy. eg Sun, March 12 2020, 4pm
     */
    public String convertDateTime() {
        String[] dateTime = this.time.split("\\s+");
        String dateOfEvent = dateTime[0];
        String timeOfEvent = dateTime[1];
        LocalDate parsedDate = LocalDate.parse(dateOfEvent);
        assert parsedDate.isAfter(LocalDate.now()) || parsedDate.isEqual(LocalDate.now());
        return parsedDate.format(DateTimeFormatter.ofPattern("E, MMM d yyyy")) + " " + timeOfEvent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DeadlineTask)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        DeadlineTask that = (DeadlineTask) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), time);
    }

    /**
     * Displays the details of the task
     * @return Formatted string which shows the type of task, its description and its expected completion date and time
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDateTime() + ")";
    }
}
