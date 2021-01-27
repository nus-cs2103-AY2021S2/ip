import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Event extends Task {
    @JsonProperty
    private LocalDateTime timeOfEvent;

    protected Event() {
        super();
    }

    /**
     * Constructs an Event object.
     * @param taskName Name of deadline task.
     * @param timeOfEvent Time at which event will take place.
     */
    public Event(String taskName, LocalDateTime timeOfEvent) {
        super(taskName);
        this.timeOfEvent = timeOfEvent;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        return String.format("[E][%s] %s (at: %s)", done ? "X" : " ", taskName, this.timeOfEvent.format(formatter));
    }
}
