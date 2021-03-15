import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task {

    private LocalDateTime deadline;

    /**
     * Constructor
     * @param info Task info
     * @param deadline date in the form "d/M/yyyy HHmm"
     */
    public DeadlineTask(String info, String deadline) {
        super(info);
        this.deadline = LocalDateTime.parse(deadline, DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
    }

    public String getDeadline() {
        return deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), getDeadline());
    }
}
