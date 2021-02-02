import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    /**
     * Returns an Event
     *
     * @param description description of the event
     * @param time        , which is currently still in String form but I suspect that might change
     **/
    public Event(String description, String time) {
        super(description);
        /* sorted should have 4 args, delimited by spaces
        1. Day
        2. Month
        3. Year
        4. Hour/Minute
         */
        String[] sorted = time.split(" ");
        this.time = LocalDateTime.of(Integer.parseInt(sorted[2]), //Day
                Integer.parseInt(sorted[1]), //Month
                Integer.parseInt(sorted[0]), //Year
                Integer.parseInt(sorted[3].substring(0, 2)), //Hour
                Integer.parseInt(sorted[3].substring(2, 4)));//Minute
    }

    /* format [D][X] {description} ({dayofweek} {time}, {day} {month} {year})
     */
    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        String day = time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        return String.format("%s%s %s (%s %s:%s, %s %s %s)", type, doneStatus, this.description, day,
                time.getHour(), time.getMinute(), time.getDayOfMonth(), time.getMonth(), time.getYear());
    }

    LocalDateTime time;
}
