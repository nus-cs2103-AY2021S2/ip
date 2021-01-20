public class Event extends Task{

  protected String by;

  public Event(String description, String by) {

    super(description);
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + " (at: " + this.by + ")";
  }
}
