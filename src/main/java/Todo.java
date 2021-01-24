public class Todo extends Task {
  public Todo(String description) {
    super(description);
  }

  public Todo(String description, boolean isDone) {
    super(description, isDone);
  }

  @Override
  public String toString() {
    return String.format("[T]%s", super.toString());
  }

  public String toSavedString() {
    return String.format("T | %d | %s", super.isDone ? 1 : 0, super.description);
  }
}
