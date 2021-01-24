public class Deadline extends Task {
  protected String deadline;

  public Deadline(String description, String deadline) {
    super(description);
    this.deadline = deadline;
  }

  @Override
  public String toString() {
    return String.format("[D]%s (by: %s)", super.toString(), deadline);
  }

  public String toSavedString() {
    return String.format("D | %d | %s | %s", super.isDone ? 1 : 0, super.description, deadline);
  }
}
