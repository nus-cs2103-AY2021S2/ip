public class Event extends Task {
  protected String time;

  public Event(String description, String time) {
    super(description);
    this.time = time;
  }

  @Override
  public String toString() {
    return String.format("[E]%s (at: %s)", super.toString(), time);
  }

  public String toSavedString() {
    return String.format("E | %d | %s | %s", super.isDone ? 1 : 0, super.description, time);
  }
}
