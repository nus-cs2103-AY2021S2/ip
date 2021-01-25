package Task;

import Utils.DateTime;

import java.time.LocalDate;

public class Event extends Task {
    protected LocalDate at;

    public Event(String description, LocalDate at) {
        super(description);
        this.at = at;
    }

    @Override
    public String serialise() {
        String type = "EVENT";
        StringBuilder sb = new StringBuilder();
        sb.append(type).append('|').append(isDone).append('|').append(description).append('|').append(at);

        return sb.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + DateTime.getDate(at) + ")";
    }
}
