import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
  protected LocalDate time;

  public Event(String description, String time) {
    super(description);
    this.time = LocalDate.parse(time);
  }

  @Override
  public String toString() {
    return String.format("[E]%s (at: %s)", super.toString(), time.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
  }

  public String toSavedString() {
    return String.format("E | %d | %s | %s", super.isDone ? 1 : 0, super.description, time);
  }
}
