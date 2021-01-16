public class Task {
  protected String description;
  protected boolean isDone;

  public Task(String description) {
      this.description = description;
      this.isDone = false;
  }

  @Override
  public String toString() {
    return String.format("[%s] %s", isDone ? "X" : " ", description);
  }
}