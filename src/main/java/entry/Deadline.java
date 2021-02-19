package entry;

import command.CommandFormatException;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    LocalDateTime deadline;

    /**
     * Returns a Task.Deadline
     * sorted should have 4 args, delimited by spaces
     *  1. Day
     *  2. Month
     *  3. Year
     *  4. Hour/Minute
     * @param description: description of the deadline
     * @param deadline: String to be converted to a LocalDateTime
     **/
    public Deadline(String description, String deadline) throws CommandFormatException {
        super(description);
        try {
            this.deadline = parseDate(deadline.split(" "));
        } catch (NumberFormatException e){
            throw new CommandFormatException(e.getMessage());
        }
    }

    /** Called during initialisation only, when storage pulls stored tasks from txt file.
     * @param isDone: boolean to indicate whether Task is done
     * @param description: description of the deadline
     * @param deadline: String to be converted to a LocalDateTime
     * @throws CommandFormatException user has typed the command in a wrong format
     */
    public Deadline(Boolean isDone, String description, String deadline) throws CommandFormatException{
        super(isDone, description);
        try {
            this.deadline = parseDate(deadline.split(" "));
        } catch (NumberFormatException e){
            throw new CommandFormatException(e.getMessage());
        }
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    /** Parses the String to produce a LocalDateTime. If information is wrong, throws CommandFormatException,
     * which will be eventually handled to remind users to use correct format. See Ui.
     * @param sorted: the sorted tags corresponding to various "time" arguments
     * @return LocalDateTime
     * @throws CommandFormatException: user has typed the command in a wrong format
     */
    private LocalDateTime parseDate(String[] sorted) throws CommandFormatException{
        try {
            return LocalDateTime.of(Integer.parseInt(sorted[2]), //Day
                    Integer.parseInt(sorted[1]), //Month
                    Integer.parseInt(sorted[0]), //Year
                    Integer.parseInt(sorted[3].substring(0, sorted[3].length() - 2)), //Hour
                    Integer.parseInt(sorted[3].substring(sorted[3].length() - 2)));//Minute
        } catch (NumberFormatException | DateTimeException | ArrayIndexOutOfBoundsException e) {
            throw new CommandFormatException(e.getMessage());
        }
    }

    /**
     * @return String representation of this Deadline
     * format [D][X] {description} ({dayofweek} {time}, {day} {month} {year})
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

    /**
     * @return wrapper around LocalDateTime corresponding method, but well formatted
     */
    private String getDeadlineHour() {
        assert(this.deadline != null);
        return this.deadline.getHour() < 10 ? "0" + this.deadline.getHour() : String.valueOf(this.deadline.getHour());
    }

    /**
     * @return wrapper around LocalDateTime corresponding method, but well formatted
     */
    private String getDeadlineMinute() {
        assert(this.deadline != null);
        return this.deadline.getMinute() < 10 ? "0" + this.deadline.getMinute() : String.valueOf(this.deadline.getMinute());
    }

    /***
     * Format = {type}{done}{description}{deadline}
     * @return String to be stored in the txt file for storage.
     */
    public String toStorage() {
        assert(!this.description.isEmpty()); // important assertions to not write rubbish into storage file
        assert(this.deadline != null);
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
}
