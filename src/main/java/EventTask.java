import java.time.LocalDate;

public class EventTask extends Task {
    private LocalDate event;

    public EventTask(String taskDescription, LocalDate event) {
        super(taskDescription);
        this.event = event;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.event);
    }
}
