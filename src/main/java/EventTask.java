public class EventTask extends Task {

  protected String at;

  public EventTask(String name, String at) {
    super(name);
    this.at = at;
  }

  @Override
  public String toString() {
    return "[E]" + super.toString() + "(at:" + this.at + ")";
  }
}
