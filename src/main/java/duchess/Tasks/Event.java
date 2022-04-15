package duchess.Tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    /** Time of event */
    protected LocalDate time;

    /** Constructs a new event
     *
     * @param name of event
     * @param time of event
     * */

    public Event(String name, String time) {
        super(name);
        this.time = LocalDate.parse(time);
        this.cat = 'E';
    }

    /** Returns time of event in the format yyyy-MM-dd
     *
     * @return Time as a string
     * */
    public String getTime() {
        return this.time.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /** Returns time of event in the format MMM d yyyy
     *
     * @return Time as a string
     * */
    public String getFormattedTime() {
        return this.time.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    @Override
    public String toString() {
        return "[" + this.cat + "] " + super.toString() + " (at: " + this.getFormattedTime() + ")";
    }
}
