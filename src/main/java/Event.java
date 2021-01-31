import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    
    protected String time;

    public final static Event createEvent(String input) throws DukeException {
        String name = input.substring(5);
        if (name.equals("") || name.equals(" ")) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }

        name = name.substring(1);
        int cutoff = name.indexOf("/at");
        if (cutoff == -1) {
            throw new DukeException("☹ OOPS!!! Events need a timing! Use \"/at\" to indicate timings!");
        }

        String eventName = name.substring(0, cutoff - 1);
        String eventTime = name.substring(cutoff + 3);
        if (eventTime.equals("") || eventTime.equals(" ")) {
            throw new DukeException("☹ OOPS!!! Events need a timing!");
        }

        eventTime = eventTime.substring(1);
        LocalDate actualTime;    
        try {   
            actualTime = LocalDate.parse(eventTime);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please enter your date in YYYY-MM-DD format!!!");
        }

        System.out.println(actualTime.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
        return new Event(eventName, eventTime);
    }

    protected Event(String name, String time) {
        super(name);
        this.time = time;
    }

    protected Event(String name, String time, boolean completed) {
        super(name, completed);
        this.time = time;
    }

    @Override
    public Task completeTask() {
        return new Event(this.name, this.time, true);
    }

    @Override
    public String toString() {
        return super.toString().replace("[T]", "[E]") + " (at: " + this.time + ")";
    }

}
