import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    String eventTime;
    LocalDate localDate;
    LocalTime localTime;

    public Event(String taskName, String eventTime) {
        super(taskName);
        this.eventTime = eventTime;

        String[] eventTimeSplit = eventTime.split(" ");

        localDate = LocalDate.parse(eventTimeSplit[0]);

        if (eventTimeSplit.length > 1) {
            int time = Integer.parseInt(eventTimeSplit[1]);
            localTime = LocalTime.of(time/100, time % 100);
        } else {
            localTime = LocalTime.MIDNIGHT;
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " +
                LocalDateTime.of(localDate, localTime).format(DateTimeFormatter.ofPattern("MMM d yyy h:m a")) + ")";
    }

    @Override
    public String generateDataString() {
        return "event" + taskName + eventTime + (done ? "done" : "notDone");
    }
}
