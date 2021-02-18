import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

  protected String by;


  public Event(String description, String by) {
    super(description);
    this.by = by;
  }

  private LocalDateTime dateTime(String by) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
    return LocalDateTime.parse(by, format);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at:" + by + ")";
  }

  @Override
  public String saveText() {
    return "E" + "," + this.getStatusIcon() + "," + this.getDescription() + "," + this.by;
  }
}
