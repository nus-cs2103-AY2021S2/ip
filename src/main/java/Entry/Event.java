package Entry;

import Command.CommandFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {
    /**
     * Returns an Task.Event
     *
     * @param description description of the event
     * @param time        String to be converted to a LocalDateTime
     **/
    public Event(String description, String time) throws CommandFormatException {
        super(description);
        /* sorted should have 4 args, delimited by spaces
        1. Day
        2. Month
        3. Year
        4. Hour/Minute
         */
        try {
            this.time = parseDate(time.split(" "));
        } catch (NumberFormatException e){
            throw new CommandFormatException(e.getMessage());
        }
    }

    /** Called during initialisation only, when storage pulls stored tasks from txt file.
     * @param isDone boolean to indicate whether Task is done
     * @param description description of the Event
     * @param time String to be converted to a LocalDateTime
     * @throws CommandFormatException user has typed the command in a wrong format
     */
    public Event(Boolean isDone, String description, String time) throws CommandFormatException {
        super(isDone, description);
        try {
            this.time = parseDate(time.split(" "));
        } catch (NumberFormatException e){
            throw new CommandFormatException(e.getMessage());
        }
    }

    /** Parses the String to produce a LocalDateTime. If information is wrong, throws CommandFormatException,
     * which will be eventually handled to remind users to use correct format. See Ui.
     * @param sorted the sorted tags corresponding to various "time" arguments
     * @return LocalDateTime
     * @throws CommandFormatException user has typed the command in a wrong format
     */
    private LocalDateTime parseDate(String[] sorted) throws CommandFormatException{
        try {
            return LocalDateTime.of(Integer.parseInt(sorted[2]), //Day
                    Integer.parseInt(sorted[1]), //Month
                    Integer.parseInt(sorted[0]), //Year
                    Integer.parseInt(sorted[3].substring(0, sorted[3].length()-2)), //Hour
                    Integer.parseInt(sorted[3].substring(sorted[3].length()-2)));//Minute
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new CommandFormatException(e.getMessage());
        }
    }

    /**
     * @return String representation of this Event
     * format [D][X] {description} ({dayofweek} {time}, {day} {month} {year})
     */
    @Override
    public String toString() {
        String type = "[E]";
        String doneStatus = "[" + getStatusIcon() + "]";
        String day = time.getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.forLanguageTag("en"));
        return String.format("%s%s %s (%s %s:%s, %s %s %s)", type, doneStatus, this.description, day,
                this.getTimeHour(), this.getTimeMinute(), time.getDayOfMonth(), time.getMonth(), time.getYear());
    }

    /**
     * @return wrapper around LocalDateTime corresponding method, but well formatted
     */
    private String getTimeHour(){
        return this.time.getHour() < 10 ? "0" + this.time.getHour(): String.valueOf(this.time.getHour());
    }

    /**
     * @return wrapper around LocalDateTime corresponding method, but well formatted
     */
    private String getTimeMinute(){
        return this.time.getMinute() < 10 ? "0" + this.time.getMinute(): String.valueOf(this.time.getMinute());
    }


    /***
     * Format = {type}{done}{description}{deadline}
     * @return String to be stored in the txt file for storage.
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
