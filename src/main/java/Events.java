import java.time.LocalDateTime;
public class Events extends Task {
    public String at;

    //by is the time/date simple string
    public Events(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + "[" + this.getStatusIcon() + "] " + this.description + " (at: " + this.at + ")";
    }
}

