import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    /**
     * Returns a Deadline
     *
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
        this.deadline = parseDate(deadline.split(" "));
    }

    public Deadline(Boolean isDone, String description, String deadline) {
        super(isDone, description);
        this.deadline = parseDate(deadline.split(" "));
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
        String type = "[D]";
        String doneStatus = "[" + getStatusIcon() + "]";
        String day = deadline.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        return String.format("%s%s %s (%s %s:%s, %s %s %s)", type, doneStatus, this.description, day,
                this.getDeadlineHour(), this.getDeadlineMinute(), deadline.getDayOfMonth(), deadline.getMonth(),
                deadline.getYear());
    }

    private String getDeadlineHour(){
        return this.deadline.getHour() < 10 ? "0" + this.deadline.getHour(): String.valueOf(this.deadline.getHour());
    }
    private String getDeadlineMinute(){
        return this.deadline.getMinute() < 10 ? "0" + this.deadline.getMinute(): String.valueOf(this.deadline.getMinute());
    }

    /***
     * Format = {type}{done}{description}{deadline}
     */
    public String toStorage() {
        //type
        String res = "D";
        //done status
        res += "\u001E" + (isDone ? "T" : "F");
        //description
        res += "\u001E" + this.description;
        //deadline
        res += "\u001E" + String.format("%s %s %s %s%s", this.deadline.getDayOfMonth(), this.deadline.getMonthValue(),
                this.deadline.getYear(), this.getDeadlineHour(), this.getDeadlineMinute());
        return res;
    }
    LocalDateTime deadline;
}
