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
        formatDate();

    }

    Deadline(String description, String eventDate, boolean isDone) {
        super(description, eventDate, isDone);
        formatDate();

    }


    public void formatDate() {
        try {
            time = LocalDateTime.parse(this.eventDate, formatter);
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
