import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Formats string to be stored.
     *
     * @return Formatted String.
     */
    @Override
    public String getSaveString() {
        return "D" + " | " + (isDone ? "1" : "0")
                + " | " + this.description + " | " + formatDateForSaving();
    }

    private String formatDate() {
        return this.by.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

  private String formatDateForSaving() {
      return this.by.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
  }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.formatDate() + ")";
    }
}