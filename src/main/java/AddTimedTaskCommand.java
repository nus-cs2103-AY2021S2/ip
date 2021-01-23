import java.time.LocalDateTime;

public abstract class AddTimedTaskCommand extends AddCommand {
    LocalDateTime dateTime;
    public AddTimedTaskCommand(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }
}
