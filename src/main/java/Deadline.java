import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM" +
            "/yyyy HHmm");

    private LocalDateTime time;

    Deadline(String description) {
        super(description);
    }

    Deadline(String description, String eventDate) {
        super(description, eventDate);

        try {
            time = LocalDateTime.parse(eventDate, formatter);
        } catch (Exception e) {
            time = null;
        }

    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s(by:%s)", this.getTaskType(),
                this.getStatusIcon(), this.getDescription(), this.getEventDate());
    }


}
