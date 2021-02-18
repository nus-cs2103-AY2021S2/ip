import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

  protected LocalDateTime by;
  protected boolean dateError = false;

  public Deadline(String description, String by) {
    super(description);
    this.by = dateTime(by);
  }

  public Deadline(String description, LocalDateTime by) {
    super(description);
    this.by = by;
  }

  private LocalDateTime dateTime(String by) {
    DateTimeFormatter format = null;
    LocalDateTime date = null;
    try {
      format = DateTimeFormatter.ofPattern("d/MM/yyyy Hmm");
      date = LocalDateTime.parse(by, format);
    } catch (DateTimeParseException e) {
      System.out.println("Please use d/MM/yyyy Hmm for your date");
      dateError = true;
    }
    return date;
  }

  private boolean getdateError() {
    return this.dateError;
  }

  @Override
  public String toString() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM dd yyyy");
    boolean exception = false;
    String s = "Please give your dates in either d/MM/yyyy Hmm";
    try {
      by.format(format);
    } catch (NullPointerException e) {
      System.out.println("Wrong date format");
      exception = true;
    }
    if (!exception) {
      return "[D]" + super.toString() + " (by: " + by.format(format) + ")";
    } else {
      return s;
    }

  }

  @Override
  public String saveText() {
    return "D" + "," + this.getStatusIcon() + "," + this.getDescription() + "," + this.by;
  }



}