import java.time.LocalDate;

public class Deadline extends Task {
    private final LocalDate deadline;

    public Deadline(String description, LocalDate deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String data() {
        return String.format("D | %s| %s", super.data(), deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), Parser.localDateToString(deadline));
    }
}
