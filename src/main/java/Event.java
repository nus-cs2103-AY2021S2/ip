import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  protected LocalDateTime by;

  public Event(String description, String by) {
    super(description);
    this.by = dateTime(by);
  }

  private LocalDateTime dateTime(String by) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
    return LocalDateTime.parse(by, format);
  }

  @Override
  public String toString() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
    return "[E]" + super.toString() + " (at:" + by.format(format) + ")";
  }

  @Override
  public String saveText() {
    return "E" + "," + this.getStatusIcon() + "," + this.getDescription() + "," + this.by;
  }
}
