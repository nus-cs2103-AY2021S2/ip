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
        this.time = parseDate(time.split(" "));
    }

    public Event(Boolean isDone, String description, String time) {
        super(isDone, description);
        this.time = parseDate(time.split(" "));
    }

    private LocalDateTime parseDate(String[] sorted){
        return LocalDateTime.of(Integer.parseInt(sorted[2]), //Day
                Integer.parseInt(sorted[1]), //Month
                Integer.parseInt(sorted[0]), //Year
                Integer.parseInt(sorted[3].substring(0, sorted[3].length()-2)), //Hour
                Integer.parseInt(sorted[3].substring(sorted[3].length()-2)));//Minute
    }

    /* format [D][X] {description} ({dayofweek} {time}, {day} {month} {year})
     */
    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        String day = time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        return String.format("%s%s %s (%s %s:%s, %s %s %s)", type, doneStatus, this.description, day,
                this.getTimeHour(), this.getTimeMinute(), time.getDayOfMonth(), time.getMonth(), time.getYear());
    }

    private String getTimeHour(){
        return this.time.getHour() < 10 ? "0" + this.time.getHour(): String.valueOf(this.time.getHour());
    }
    private String getTimeMinute(){
        return this.time.getMinute() < 10 ? "0" + this.time.getMinute(): String.valueOf(this.time.getMinute());
    }


    /***
     * Format = {type}{done}{description}{deadline}
     */
    public String toStorage(){
        //type
        String res = "D";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        //time
        res += "\u001E" + String.format("%s %s %s %s%s", this.time.getDayOfMonth(), this.time.getMonthValue(),
                this.time.getYear(), this.getTimeHour(), this.getTimeMinute());
        return res;
    }
    LocalDateTime time;
}
