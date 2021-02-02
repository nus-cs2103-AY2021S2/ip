import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task{
    /**
     * Returns a Deadline
     * @param description description of the deadline
     * @param deadline , which is currently still in String form but I suspect that might change
     * **/
    public Deadline(String description, String deadline) {
        super(description);
        /* sorted should have 4 args, delimited by spaces
        1. Day
        2. Month
        3. Year
        4. Hour/Minute
         */
        String[] sorted = deadline.split(" ");
        this.deadline = LocalDateTime.of(Integer.parseInt(sorted[2]), //Day
                Integer.parseInt(sorted[1]), //Month
                Integer.parseInt(sorted[0]), //Year
                Integer.parseInt(sorted[3].substring(0, 2)), //Hour
                Integer.parseInt(sorted[3].substring(2, 4)));//Minute
    }

    /* format [D][X] {description} ({dayofweek} {time}, {day} {month} {year})
     */
    @Override
    public String toString() {
        String type = "[D]";
        String doneStatus = "[" + getStatusIcon() + "]";
        String day = deadline.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        return String.format("%s%s %s (%s %s:%s, %s %s %s)", type, doneStatus, this.description, day, deadline.getHour(),
                deadline.getMinute(), deadline.getDayOfMonth(), deadline.getMonth(), deadline.getYear());
    }

    LocalDateTime deadline;
}
